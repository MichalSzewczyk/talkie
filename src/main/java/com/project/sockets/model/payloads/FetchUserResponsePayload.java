package com.project.sockets.model.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"users"})
public class FetchUserResponsePayload {

    public FetchUserResponsePayload(List<String> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    @JsonProperty("users")
    private List<String> listOfUsers;

    @JsonProperty("users")
    public List<String> getListOfUsers() {
        return listOfUsers;
    }

    @JsonProperty("users")
    public void setListOfUsers(List<String> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }
}