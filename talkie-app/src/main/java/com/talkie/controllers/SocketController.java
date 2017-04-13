package com.talkie.controllers;

import com.talkie.sockets.model.messages.SocketMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @MessageMapping("/")
    @SendTo("/")
    public SocketMessage getSocketMessage(SocketMessage socketMessage) {

        return socketMessage;
    }
}