package com.talkie.dialect.messages.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.talkie.dialect.MessageType;
import com.talkie.dialect.messages.model.User;
import com.talkie.dialect.payloads.FindUserResponsePayload;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "payload"})
public class FindUserResponse implements SocketResponseMessage, Serializable {

    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("payload")
    private FindUserResponsePayload payload;
    public FindUserResponse(Integer id, List<User> users) {
        this.payload = new FindUserResponsePayload(users);
        this.id = id;
        this.type = MessageType.FIND_USER_RESPONSE.toString();
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
    public FindUserResponsePayload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(FindUserResponsePayload payload) {
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