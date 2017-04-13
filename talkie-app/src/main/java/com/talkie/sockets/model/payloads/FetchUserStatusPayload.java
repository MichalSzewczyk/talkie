package com.talkie.sockets.model.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"listOfUsers"})
public class FetchUserStatusPayload {

    @JsonProperty("listOfUsers")
    private List<Integer> listOfUsers;

    @JsonProperty("listOfUsers")
    public List<Integer> getListOfUsers() {
        return listOfUsers;
    }

    @JsonProperty("listOfUsers")
    public void setListOfUsers(List<Integer> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }
}