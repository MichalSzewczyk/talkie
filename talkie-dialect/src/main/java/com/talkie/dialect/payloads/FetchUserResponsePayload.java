package com.talkie.dialect.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"users"})
public class FetchUserResponsePayload {

    public FetchUserResponsePayload(List<Integer> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    @JsonProperty("users")
    private List<Integer> listOfUsers;

    @JsonProperty("users")
    public List<Integer> getListOfUsers() {
        return listOfUsers;
    }

    @JsonProperty("users")
    public void setListOfUsers(List<Integer> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }
}