package com.project.sockets.model.messages.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.sockets.model.messages.TextMessage;
import com.project.sockets.model.messages.requests.SendMessage;

import static com.project.sockets.model.MessageType.RECEIVE_MESSAGE;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "id", "payload"})
public class ReceiveMessage extends TextMessage {
    public ReceiveMessage() {
    }

    public ReceiveMessage(SendMessage sendMessage) {
        super(sendMessage, RECEIVE_MESSAGE);
    }

}