package com.project.sockets.model.messages.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.project.sockets.model.payloads.FetchUserResponsePayload;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "payload"})
public class FetchUserStatusResponse implements SocketResponseMessage {

    public FetchUserStatusResponse(List<String> ids) {
        payload = new FetchUserResponsePayload(ids);
    }

    @JsonProperty("type")
    private String type;

    @JsonProperty("id")
    private String id;
    @JsonProperty("payload")
    private FetchUserResponsePayload payload;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("payload")
    public FetchUserResponsePayload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(FetchUserResponsePayload payload) {
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