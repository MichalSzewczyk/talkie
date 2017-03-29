package com.project.sockets.model.messages.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.sockets.model.payloads.SendMessagePayload;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type","payload"})
public class SendMessage implements SocketRequestMessage {

    @JsonProperty("type")
    private String type;

    @JsonProperty("payload")
    private SendMessagePayload payload;

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

}