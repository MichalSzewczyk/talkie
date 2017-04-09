package com.project.sockets.handlers;

import com.project.sockets.model.messages.requests.FetchUserStatus;
import com.project.sockets.model.messages.requests.SendMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractHandlingService {
    protected final Map<Long, WebSocketSession> loggedInUsers = new ConcurrentHashMap<>();

    public abstract void handleFetchUserStatus(FetchUserStatus fetchUserStatus, WebSocketSession session);

    public abstract void handleSendMessage(SendMessage sendMessage);
}
