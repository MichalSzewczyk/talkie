package com.project.sockets.handlers;

import com.project.sockets.exceptions.NoSuchUserException;
import com.project.sockets.interfaces.ParsingService;
import com.project.sockets.model.MessageType;
import com.project.sockets.model.messages.requests.FetchUserStatus;
import com.project.sockets.model.messages.requests.SendMessage;
import com.project.sockets.model.messages.responses.FetchUsersStatusResponse;
import com.project.sockets.model.payloads.FetchUsersResponsePayload;
import com.project.sockets.model.payloads.UserElement;
import com.project.utils.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.project.sockets.model.MessageType.USERS_STATUS;

@Component
public class MessageHandlerFacade extends TextWebSocketHandler {
    private final Logger logger = LoggerFactory.getLogger(MessageHandlerFacade.class);

    private final List<WebSocketSession> establishedSessions;
    private final Map<Long, WebSocketSession> loggedInUsers;

    private static final String RECEIVER_NOT_FOUND = "Message receiver: %s not found. Required session is not available or user is not logged in.";
    private static final String NOT_SUPPORTED_MESSAGE = "Not supported message type: %s";
    private static final String ESTABLISHED_CONNECTION = "Established connection with user: %s";
    private static final String MESSAGE_SENT = "Message: %s sent to user with id: %s";
    private static final String HANDLING_MESSAGE = "Handling socket message: %s";
    private static final String SERIALIZATION_FAILED = "Unable to serialize %s";
    private static final String LOGGED_IN_USERS = "Users logged in: %s when user %s is logging in.";

    private ParsingService parsingService;

    @Autowired
    public MessageHandlerFacade(ParsingService parsingService) {
        this.parsingService = parsingService;
        establishedSessions = new CopyOnWriteArrayList<>();
        loggedInUsers = new ConcurrentHashMap<>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        establishedSessions.add(session);
        logger.info(String.format(ESTABLISHED_CONNECTION, session.getUri()));
    }


    private void sendMessage(Long receiverId, String message) throws IOException {
        WebSocketSession socketSession = loggedInUsers.get(receiverId);

        if (socketSession == null) {
            logger.info(String.format(RECEIVER_NOT_FOUND, receiverId));
            throw new NoSuchUserException("Couldn't find required user.");
        }

        socketSession.sendMessage(new TextMessage(message));
        logger.info(String.format(MESSAGE_SENT, message, receiverId));
    }

    private void handleFetchUserStatus(FetchUserStatus fetchUserStatus, WebSocketSession session) {
        List<String> friends = fetchUserStatus.getPayload().getListOfUsers();
        Long userID = Long.parseLong(fetchUserStatus.getId());
        loggedInUsers.put(userID, session);
        logger.info(String.format(LOGGED_IN_USERS, loggedInUsers, userID));
        FetchUsersStatusResponse response = new FetchUsersStatusResponse(USERS_STATUS.toString(), new FetchUsersResponsePayload(prepareUserElements(friends, loggedInUsers.keySet())));
        Optional<String> responseMessage = parsingService.serialize(response);

        if(!responseMessage.isPresent()){
            String errorInfo = String.format(SERIALIZATION_FAILED, USERS_STATUS);
            logger.error(errorInfo);
            throw new RuntimeException(errorInfo);
        }

        try {
            sendMessage(userID, responseMessage.get());
        } catch (IOException e) {
            //TODO: how should we solve lack of sender
        }
    }

    private UserElement[] prepareUserElements(List<String> friends, Set<Long> loggedIn){
        return friends.stream().map(Long::parseLong).map(friendId -> new UserElement(String.valueOf(friendId), (loggedIn.contains(friendId))?"1":"0")).toArray(UserElement[]::new);
    }

    private void handleSendMessage(SendMessage sendMessage) {
        Long receiverId = Long.parseLong(sendMessage.getPayload().getReceiverId());
        String message = sendMessage.getPayload().getBody();
        try {
            sendMessage(receiverId, message);
        } catch (IOException e) {
            //TODO: inform sender about lack of recipient
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String plainTextMessage = message.getPayload();
        Tuple<Object, MessageType> result = parsingService.parseSocketMessage(plainTextMessage);
        logger.info(String.format(HANDLING_MESSAGE, message.getPayload()));
        switch (result.getValue()) {
            case FETCH_USER_STATUS:
                handleFetchUserStatus((FetchUserStatus) result.getKey(), session);
                break;
            case SEND_MESSAGE:
                handleSendMessage((SendMessage) result.getKey());
                break;
            default:
                String info = String.format(NOT_SUPPORTED_MESSAGE, result.getValue());
                logger.error(info);
                throw new UnsupportedOperationException(info);
        }
    }
}