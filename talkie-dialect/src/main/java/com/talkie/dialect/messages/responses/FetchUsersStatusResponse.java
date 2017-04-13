package com.talkie.dialect.messages.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.talkie.dialect.payloads.FetchUsersResponsePayload;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "payload"})
public class FetchUsersStatusResponse implements SocketResponseMessage, Serializable {

    public FetchUsersStatusResponse(String type, FetchUsersResponsePayload payload) {
        this.type = type;
        this.payload = payload;
    }

    public FetchUsersStatusResponse() {}

    @JsonProperty("type")
    private String type;

    @JsonProperty("payload")
    private FetchUsersResponsePayload payload;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("payload")
    public FetchUsersResponsePayload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(FetchUsersResponsePayload payload) {
        this.payload = payload;
    }

}