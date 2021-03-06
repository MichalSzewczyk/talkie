package com.talkie.dialect.messages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.talkie.dialect.MessageType;
import com.talkie.dialect.messages.requests.SendMessage;
import com.talkie.dialect.payloads.SendMessagePayload;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "id", "payload"})
public class TextMessage implements SocketMessage, Serializable {
    @JsonProperty("type")
    private String type;
    @JsonProperty("payload")
    private SendMessagePayload payload;
    @JsonProperty("id")
    private Integer id;

    public TextMessage() {
    }

    public TextMessage(SendMessage sendMessage, MessageType messageType) {
        this.payload = sendMessage.getPayload();
        this.id = sendMessage.getId();
        this.type = messageType.toString();
    }

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
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }
}