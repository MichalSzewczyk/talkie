package com.project.sockets.model.messages.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.database.model.User;
import com.project.sockets.model.MessageType;
import com.project.sockets.model.payloads.FindUserResponsePayload;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "payload"})
public class FindUserResponse implements SocketResponseMessage, Serializable {

    public FindUserResponse(String id, List<User> users) {
        this.payload = new FindUserResponsePayload(users);
        this.id = id;
        this.type = MessageType.FIND_USER_RESPONSE.toString();
    }

    @JsonProperty("type")
    private String type;

    @JsonProperty("id")
    private String id;
    @JsonProperty("payload")
    private FindUserResponsePayload payload;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("payload")
    public FindUserResponsePayload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(FindUserResponsePayload payload) {
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
}