package com.talkie.sockets.model.messages.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.talkie.sockets.model.MessageType;
import com.talkie.sockets.model.messages.TextMessage;
import com.talkie.sockets.model.messages.requests.SendMessage;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "id", "payload"})
public class ReceiveMessage extends TextMessage {
    public ReceiveMessage() {
    }

    public ReceiveMessage(SendMessage sendMessage) {
        super(sendMessage, MessageType.RECEIVE_MESSAGE);
    }

}