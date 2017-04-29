package com.talkie.utils;

import com.talkie.dialect.messages.requests.FetchUserStatus;
import com.talkie.dialect.messages.requests.FindUser;
import com.talkie.dialect.messages.requests.SendMessage;
import com.talkie.sockets.handlers.AbstractHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageHandlingVisitor extends HandlingVisitor {
    private final AbstractHandlingService handlingService;

    @Autowired
    public MessageHandlingVisitor(AbstractHandlingService handlingService) {
        this.handlingService = handlingService;
    }

    @Override
    public void visit(FetchUserStatus fetchUserStatus) {
        handlingService.handleFetchUserStatus(fetchUserStatus, session);
    }

    @Override
    public void visit(SendMessage sendMessage) {
        handlingService.handleSendMessage(sendMessage);
    }

    @Override
    public void visit(FindUser findUser) {
        handlingService.handleFindUser(findUser, session);
    }
}
