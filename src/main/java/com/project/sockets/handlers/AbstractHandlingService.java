package com.project.sockets.handlers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.project.sockets.model.messages.requests.FetchUserStatus;
import com.project.sockets.model.messages.requests.SendMessage;
import org.springframework.web.socket.WebSocketSession;

public abstract class AbstractHandlingService {
    protected final BiMap<Integer, WebSocketSession> biDirectionalIdAndSessionMapping = HashBiMap.create();

    public abstract void handleFetchUserStatus(FetchUserStatus fetchUserStatus, WebSocketSession session);

    public abstract void handleSendMessage(SendMessage sendMessage);

    public abstract void handleLogout(WebSocketSession session);
}
