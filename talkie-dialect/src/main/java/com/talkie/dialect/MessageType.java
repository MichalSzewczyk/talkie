package com.talkie.dialect;

import com.talkie.dialect.messages.SocketMessage;
import com.talkie.dialect.messages.requests.FetchUserStatus;
import com.talkie.dialect.messages.requests.FindUser;
import com.talkie.dialect.messages.requests.SendMessage;
import com.talkie.dialect.messages.responses.FetchUsersStatusResponse;
import com.talkie.dialect.messages.responses.FindUserResponse;
import com.talkie.dialect.messages.responses.ReceiveMessage;

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
