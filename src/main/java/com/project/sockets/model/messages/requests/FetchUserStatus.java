package com.project.sockets.model.messages.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.sockets.model.payloads.FetchUserStatusPayload;
import com.project.utils.HandlingVisitor;
import com.project.utils.Visitable;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "payload"})
public class FetchUserStatus implements SocketRequestMessage, Visitable {

    @JsonProperty("type")
    private String type;

    @JsonProperty("id")
    private String id;
    @JsonProperty("payload")
    private FetchUserStatusPayload payload;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("payload")
    public FetchUserStatusPayload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(FetchUserStatusPayload payload) {
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