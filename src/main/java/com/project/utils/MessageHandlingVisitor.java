package com.project.utils;

import com.project.sockets.handlers.AbstractHandlingService;
import com.project.sockets.model.messages.requests.FetchUserStatus;
import com.project.sockets.model.messages.requests.SendMessage;
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
}
