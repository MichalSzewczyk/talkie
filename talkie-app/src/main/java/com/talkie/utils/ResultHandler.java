package com.talkie.utils;

import com.talkie.dialect.MessageType;
import com.talkie.dialect.messages.SocketMessage;
import com.talkie.dialect.utils.Tuple;
import org.springframework.web.socket.WebSocketSession;


public abstract class ResultHandler {
    protected WebSocketSession session;

    public abstract void handle(Tuple<? extends SocketMessage, MessageType> tuple);

    public void setSession(WebSocketSession session) {
        this.session = session;
    }
}
