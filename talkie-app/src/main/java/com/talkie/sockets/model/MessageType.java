package com.talkie.sockets.model;

import com.talkie.sockets.model.messages.SocketMessage;
import com.talkie.sockets.model.messages.requests.*;
import com.talkie.sockets.model.messages.responses.FetchUsersStatusResponse;
import com.talkie.sockets.model.messages.responses.FindUserResponse;
import com.talkie.sockets.model.messages.responses.ReceiveMessage;

public enum MessageType {
    SEND_MESSAGE(SendMessage.class),
    RECEIVE_MESSAGE(ReceiveMessage.class),
    FETCH_USER_STATUS(FetchUserStatus.class),
    USERS_STATUS(FetchUsersStatusResponse.class),
    FIND_USER(FindUser.class),
    FIND_USER_RESPONSE(FindUserResponse.class),;

    private final Class<? extends SocketMessage> socketMessageClass;

    MessageType(Class<? extends SocketMessage> socketMessageClass) {
        this.socketMessageClass = socketMessageClass;
    }

    public Class<? extends SocketMessage> getSocketMessageClass() {
        return socketMessageClass;
    }
}
