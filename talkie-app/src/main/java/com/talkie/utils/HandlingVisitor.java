package com.talkie.utils;

import com.talkie.dialect.messages.requests.FetchUserStatus;
import com.talkie.dialect.messages.requests.FindUser;
import com.talkie.dialect.messages.requests.SendMessage;
import org.springframework.web.socket.WebSocketSession;


public abstract class HandlingVisitor {
    protected WebSocketSession session;
    public abstract void visit(FetchUserStatus fetchUserStatus);
    public abstract void visit(SendMessage fetchUserStatus);
    public abstract void visit(FindUser findUser);

    public void setSession(WebSocketSession session) {
        this.session = session;
    }
}
