package com.project.sockets.model.messages.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"listOfUsers"})
public class FetchUserStatusPayload {

    @JsonProperty("listOfUsers")
    private List<String> listOfUsers;

    @JsonProperty("listOfUsers")
    public List<String> getListOfUsers() {
        return listOfUsers;
    }

    @JsonProperty("listOfUsers")
    public void setListOfUsers(List<String> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

}