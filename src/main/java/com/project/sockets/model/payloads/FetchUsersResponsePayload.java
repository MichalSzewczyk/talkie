package com.project.sockets.model.payloads;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"payload"})
public class FetchUsersResponsePayload {
	public FetchUsersResponsePayload(UserElement[] users) {
		this.users = users;
	}

	public FetchUsersResponsePayload() {}

	@JsonProperty("users")
	private UserElement[] users;

	@JsonProperty("users")
 	public void setUsers(UserElement[] users) {
		this.users = users;
	}

	@JsonProperty("users")
	public UserElement[] getUsers() {
		return users;
	}

}