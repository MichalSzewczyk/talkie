package com.talkie.dialect.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.talkie.dialect.messages.model.User;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"listOfUsers"})
public class FindUserResponsePayload {
    @JsonProperty("listOfUsers")
    private List<User> listOfUsers;

    public FindUserResponsePayload(List<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    @JsonProperty("listOfUsers")
    public List<User> getListOfUsers() {
        return listOfUsers;
    }

    @JsonProperty("listOfUsers")
    public void setListOfUsers(List<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }
}