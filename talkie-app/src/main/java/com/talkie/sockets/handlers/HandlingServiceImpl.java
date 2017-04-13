package com.talkie.sockets.handlers;

import com.talkie.database.interfaces.AccessService;
import com.talkie.database.model.User;
import com.talkie.sockets.exceptions.NoSuchUserException;
import com.talkie.sockets.interfaces.ParsingService;
import com.talkie.sockets.model.MessageType;
import com.talkie.sockets.model.messages.responses.FindUserResponse;
import com.talkie.sockets.model.messages.requests.FetchUserStatus;
import com.talkie.sockets.model.messages.requests.FindUser;
import com.talkie.sockets.model.messages.requests.SendMessage;
import com.talkie.sockets.model.messages.responses.FetchUsersStatusResponse;
import com.talkie.sockets.model.messages.responses.ReceiveMessage;
import com.talkie.sockets.model.payloads.FetchUsersResponsePayload;
import com.talkie.sockets.model.payloads.UserElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    private static final String NOTIFYING_FRIENDS = "Notifying friends of user: %s";

    private ParsingService parsingService;
    private AccessService accessService;


    @Autowired
    public HandlingServiceImpl(ParsingService parsingService, AccessService accessService) {
        this.parsingService = parsingService;
        this.accessService = accessService;
    }

    @Override
    public void handleFetchUserStatus(FetchUserStatus fetchUserStatus, WebSocketSession session) {
        List<Integer> friends = fetchUserStatus.getPayload().getListOfUsers();
        Integer userID = fetchUserStatus.getId();
        biDirectionalIdAndSessionMapping.put(userID, session);
        logger.info(String.format(LOGGED_IN_USERS, biDirectionalIdAndSessionMapping, userID));
        sendUserStatusMessage(userID, friends);
    }

    @Override
    public void handleSendMessage(SendMessage sendMessage) {
        Integer receiverId = sendMessage.getPayload().getReceiverId();
        String message = sendMessage.getPayload().getBody();
        logger.info(String.format(SENDING_MESSAGE, message, receiverId, sendMessage.getPayload().getTimestamp()));
        ReceiveMessage receiveMessage = new ReceiveMessage(sendMessage);
        accessService.saveMessage(sendMessage.getId(), receiverId, sendMessage.getPayload().getTimestamp().getTime(), sendMessage.getPayload().getBody());
        serializeAndSendMessage(receiverId, receiveMessage);
    }

    @Override
    public void handleLogout(WebSocketSession session) {
        int id = biDirectionalIdAndSessionMapping.inverse().get(session);
        logger.info(String.format(LOGGING_OUT_INFO, id));
        accessService.logoutUser(id);
        removeFromCache(session);
        handleNotifyAboutLogout(id);
    }

    @Override
    public void handleNotifyAboutLogout(Integer id) {
        logger.info(String.format(NOTIFYING_FRIENDS, id));
        List<Integer> loggedInFriends = accessService.getFriends(id);
        loggedInFriends.forEach(friendId -> sendUserStatusMessage(friendId, accessService.getFriends(friendId)));
    }

    @Override
    public void removeFromCache(WebSocketSession session) {
        biDirectionalIdAndSessionMapping.inverse().remove(session);
    }

    @Override
    public void handleFindUser(FindUser findUser, WebSocketSession session) {
        String letters = findUser.getPayload().getLetters();
        List<User> users = accessService.getUsersByLetters(letters);
        FindUserResponse response = new FindUserResponse(findUser.getId(), users);
        serializeAndSendMessage(findUser.getId(), response);
    }

    private UserElement[] prepareUserElements(List<Integer> friends, Set<Integer> loggedIn) {
        return friends.stream().map(friendId -> new UserElement(friendId, (loggedIn.contains(friendId)) ? "1" : "0")).toArray(UserElement[]::new);
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

    private void serializeAndSendMessage(Integer receiverId, Serializable serializable) {
        Optional<String> responseMessage = parsingService.serialize(serializable);

        if (!responseMessage.isPresent()) {
            String errorInfo = String.format(SERIALIZATION_FAILED, MessageType.USERS_STATUS);
            logger.error(errorInfo);
        }

        try {
            sendMessage(receiverId, responseMessage.orElseThrow(NullPointerException::new));
        } catch (IOException e) {
            logger.error(String.format(FAILED_TO_SEND_MESSAGE, receiverId), e);
        }
    }

    private void sendUserStatusMessage(Integer userID, List<Integer> friends) {
        FetchUsersStatusResponse response = new FetchUsersStatusResponse(MessageType.USERS_STATUS.toString(), new FetchUsersResponsePayload(prepareUserElements(friends, biDirectionalIdAndSessionMapping.keySet())));
        serializeAndSendMessage(userID, response);
    }
}
