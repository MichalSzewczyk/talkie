package com.project.sockets.model.payloads;

public class UserElement {
	private String id;
	private String status;

	public UserElement(String id, String status) {
		this.id = id;
		this.status = status;
	}

	public UserElement() {
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

 	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}


}