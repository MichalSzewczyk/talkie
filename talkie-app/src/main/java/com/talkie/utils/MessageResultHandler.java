package com.talkie.utils;

import com.talkie.dialect.MessageType;
import com.talkie.dialect.messages.SocketMessage;
import com.talkie.dialect.messages.requests.FetchUserStatus;
import com.talkie.dialect.messages.requests.FindUser;
import com.talkie.dialect.messages.requests.SendMessage;
import com.talkie.dialect.utils.Tuple;
import com.talkie.sockets.handlers.AbstractHandlingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageResultHandler extends ResultHandler {
    private final AbstractHandlingService handlingService;
    private static final String NOT_SUPPORTED_MESSAGE = "Not supported message type: %s";
    private final Logger logger = LoggerFactory.getLogger(MessageResultHandler.class);

    @Autowired
    public MessageResultHandler(AbstractHandlingService handlingService) {
        this.handlingService = handlingService;
    }

    private void handle(FetchUserStatus fetchUserStatus) {
        handlingService.handleFetchUserStatus(fetchUserStatus, session);
    }

    private void handle(SendMessage sendMessage) {
        handlingService.handleSendMessage(sendMessage);
    }

    private void handle(FindUser findUser) {
        handlingService.handleFindUser(findUser, session);
    }

    @Override
    public void handle(Tuple<? extends SocketMessage, MessageType> tuple) {
        switch (tuple.getValue()) {
            case FETCH_USER_STATUS:
                this.handle((FetchUserStatus) tuple.getKey());
                break;
            case SEND_MESSAGE:
                this.handle((SendMessage) tuple.getKey());
                break;
            case FIND_USER:
                this.handle((FindUser) tuple.getKey());
                break;
            default:
                logger.error(String.format(NOT_SUPPORTED_MESSAGE, tuple.getValue()));
        }
    }
}
