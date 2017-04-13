package com.talkie.dialect.messages.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.talkie.dialect.MessageType;
import com.talkie.dialect.messages.TextMessage;
import com.talkie.dialect.messages.requests.SendMessage;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "id", "payload"})
public class ReceiveMessage extends TextMessage {
    public ReceiveMessage() {
    }

    public ReceiveMessage(SendMessage sendMessage) {
        super(sendMessage, MessageType.RECEIVE_MESSAGE);
    }

}