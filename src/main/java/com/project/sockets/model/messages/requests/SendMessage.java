package com.project.sockets.model.messages.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.sockets.model.messages.TextMessage;
import com.project.utils.HandlingVisitor;
import com.project.utils.Visitable;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type","id", "payload"})
public class SendMessage extends TextMessage implements SocketRequestMessage, Serializable, Visitable {
    @Override
    public void accept(HandlingVisitor handlingVisitor) {
        handlingVisitor.visit(this);
    }
}