package com.project.sockets.model.messages.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.sockets.model.payloads.SendMessagePayload;
import com.project.utils.HandlingVisitor;
import com.project.utils.Visitable;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type","id", "payload"})
public class SendMessage implements SocketRequestMessage, Serializable, Visitable {

    @JsonProperty("type")
    private String type;

    @JsonProperty("payload")
    private SendMessagePayload payload;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("payload")
    public SendMessagePayload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(SendMessagePayload payload) {
        this.payload = payload;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void accept(HandlingVisitor handlingVisitor) {
        handlingVisitor.visit(this);
    }
}