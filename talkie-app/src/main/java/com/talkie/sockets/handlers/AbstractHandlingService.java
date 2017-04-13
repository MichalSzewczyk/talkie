package com.talkie.sockets.handlers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.talkie.dialect.messages.requests.FetchUserStatus;
import com.talkie.dialect.messages.requests.FindUser;
import com.talkie.dialect.messages.requests.SendMessage;
import org.springframework.web.socket.WebSocketSession;

public abstract class AbstractHandlingService {
    protected final BiMap<Integer, WebSocketSession> biDirectionalIdAndSessionMapping = HashBiMap.create();

    public abstract void handleFetchUserStatus(FetchUserStatus fetchUserStatus, WebSocketSession session);

    public abstract void handleSendMessage(SendMessage sendMessage);

    public abstract void handleLogout(WebSocketSession session);

    public abstract void handleNotifyAboutLogout(Integer id);

    public abstract void removeFromCache(WebSocketSession session);

    public abstract void handleFindUser(FindUser findUser, WebSocketSession session);
}

