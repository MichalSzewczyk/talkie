package com.talkie.sockets.model.messages.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.talkie.sockets.model.messages.TextMessage;
import com.talkie.utils.HandlingVisitor;
import com.talkie.utils.Visitable;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type","id", "payload"})
public class SendMessage extends TextMessage implements SocketRequestMessage, Serializable, Visitable {
    @Override
    public void accept(HandlingVisitor handlingVisitor) {
        handlingVisitor.visit(this);
    }
}