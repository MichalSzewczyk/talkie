package com.talkie.dialect.messages.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.talkie.dialect.messages.TextMessage;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type","id", "payload"})
public class SendMessage extends TextMessage implements SocketRequestMessage, Serializable {
}