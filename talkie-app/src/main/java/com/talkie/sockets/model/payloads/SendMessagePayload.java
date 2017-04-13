package com.talkie.sockets.model.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"timestamp", "body"})
public class SendMessagePayload {
    @JsonProperty("timestamp")
    private Timestamp timestamp;
    @JsonProperty("body")
    private String body;
    @JsonProperty("receiverId")
    private Integer receiverId;

    @JsonProperty("timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Timestamp timestamp) {
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
    public Integer getReceiverId() {
        return receiverId;
    }

    @JsonProperty("receiverId")
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }
}