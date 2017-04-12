package com.project.sockets.handlers;

import com.project.database.interfaces.AccessService;
import com.project.sockets.exceptions.NoSuchUserException;
import com.project.sockets.interfaces.ParsingService;
import com.project.sockets.model.messages.requests.FetchUserStatus;
import com.project.sockets.model.messages.requests.SendMessage;
import com.project.sockets.model.messages.responses.FetchUsersStatusResponse;
import com.project.sockets.model.messages.responses.ReceiveMessage;
import com.project.sockets.model.payloads.FetchUsersResponsePayload;
import com.project.sockets.model.payloads.UserElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.project.sockets.model.MessageType.USERS_STATUS;

@Service
public class HandlingServiceImpl extends AbstractHandlingService {
    private final Logger logger = LoggerFactory.getLogger(HandlingServiceImpl.class);

    private static final String SESSION_INFO = "Got session %s for id %s from id logged in users: %s";
    private static final String RECEIVER_NOT_FOUND = "Message receiver: %s not found. Required session is not available or user is not logged in.";
    private static final String LOGGED_IN_USERS = "Users logged in: %s when user %s is fetching users.";
    private static final String SERIALIZATION_FAILED = "Unable to serialize %s";
    private static final String FAILED_TO_SEND_MESSAGE = "Failed to send message to user: %s";
    private static final String SENDING_MESSAGE = "Sending message: %s to user %s with timestamp %s";
    private static final String LOGGING_OUT_INFO = "Logging out user with id: %s";

    private ParsingService parsingService;
    private AccessService accessService;


    @Autowired
    public HandlingServiceImpl(ParsingService parsingService, AccessService accessService) {
        this.parsingService = parsingService;
        this.accessService = accessService;
    }

    private void sendMessage(Integer receiverId, String message) throws IOException {
        WebSocketSession socketSession = biDirectionalIdAndSessionMapping.get(receiverId);

        logger.info(String.format(SESSION_INFO, socketSession, receiverId, biDirectionalIdAndSessionMapping));

        if (socketSession == null) {
            logger.info(String.format(RECEIVER_NOT_FOUND, receiverId));
            throw new NoSuchUserException("Couldn't find required user.");
        }

        socketSession.sendMessage(new TextMessage(message));
    }

    @Override
    public void handleFetchUserStatus(FetchUserStatus fetchUserStatus, WebSocketSession session) {
        List<String> friends = fetchUserStatus.getPayload().getListOfUsers();
        Integer userID = Integer.parseInt(fetchUserStatus.getId());
        biDirectionalIdAndSessionMapping.put(userID, session);
        logger.info(String.format(LOGGED_IN_USERS, biDirectionalIdAndSessionMapping, userID));
        FetchUsersStatusResponse response = new FetchUsersStatusResponse(USERS_STATUS.toString(), new FetchUsersResponsePayload(prepareUserElements(friends, biDirectionalIdAndSessionMapping.keySet())));
        Optional<String> responseMessage = parsingService.serialize(response);

        if (!responseMessage.isPresent()) {
            String errorInfo = String.format(SERIALIZATION_FAILED, USERS_STATUS);
            logger.error(errorInfo);
        }

        try {
            sendMessage(userID, responseMessage.get());
        } catch (IOException e) {
            //TODO: how should we solve lack of sender
        }
    }

    @Override
    public void handleSendMessage(SendMessage sendMessage) {
        Integer receiverId = Integer.parseInt(sendMessage.getPayload().getReceiverId());
        String message = sendMessage.getPayload().getBody();
        logger.info(String.format(SENDING_MESSAGE, message, receiverId, sendMessage.getPayload().getTimestamp()));
        ReceiveMessage receiveMessage = new ReceiveMessage(sendMessage);

        Optional<String> responseMessage = parsingService.serialize(receiveMessage);

        if (!responseMessage.isPresent()) {
            String errorInfo = String.format(SERIALIZATION_FAILED, USERS_STATUS);
            logger.error(errorInfo);
        }

        try {
            sendMessage(receiverId, responseMessage.get());
            accessService.saveMessage(Integer.parseInt(sendMessage.getId()), receiverId, sendMessage.getPayload().getTimestamp().getTime(), sendMessage.getPayload().getBody());
        } catch (IOException e) {
            logger.error(String.format(FAILED_TO_SEND_MESSAGE, receiverId), e);
            //TODO: inform sender about lack of recipient
        }
    }

    @Override
    public void handleLogout(WebSocketSession session) {
        int id = biDirectionalIdAndSessionMapping.inverse().remove(session);
        logger.info(String.format(LOGGING_OUT_INFO, id));
        accessService.logoutUser(id);
    }

    private UserElement[] prepareUserElements(List<String> friends, Set<Integer> loggedIn) {
        return friends.stream().map(Integer::parseInt).map(friendId -> new UserElement(String.valueOf(friendId), (loggedIn.contains(friendId)) ? "1" : "0")).toArray(UserElement[]::new);
    }
}
