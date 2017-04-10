package com.project.sockets.model;

import com.project.sockets.model.messages.SocketMessage;
import com.project.sockets.model.messages.requests.*;
import com.project.sockets.model.messages.responses.FetchUsersStatusResponse;
import com.project.sockets.model.messages.responses.ReceiveMessage;

public enum MessageType {
    SEND_MESSAGE(SendMessage.class),
    RECEIVE_MESSAGE(ReceiveMessage.class),
    PING(Ping.class),
    CLOSE_STREAM(CloseStream.class),
    FETCH_USER_STATUS(FetchUserStatus.class),
    USERS_STATUS(FetchUsersStatusResponse.class);

    private final Class<? extends SocketMessage> socketMessageClass;

    MessageType(Class<? extends SocketMessage> socketMessageClass) {
        this.socketMessageClass = socketMessageClass;
    }

    public Class<? extends SocketMessage> getSocketMessageClass() {
        return socketMessageClass;
    }
}
