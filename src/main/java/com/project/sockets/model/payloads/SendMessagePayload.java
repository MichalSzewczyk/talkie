package com.project.sockets.model.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"timestamp", "body"})
public class SendMessagePayload {
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("body")
    private String body;
    @JsonProperty("receiverId")
    private String receiverId;

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    @JsonProperty("receiverId")
    public String getReceiverId() {
        return receiverId;
    }

    @JsonProperty("receiverId")
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
}