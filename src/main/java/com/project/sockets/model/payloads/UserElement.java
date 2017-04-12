package com.project.sockets.model.payloads;

public class UserElement {
	private Integer id;
	private String status;

	public UserElement(Integer id, String status) {
		this.id = id;
		this.status = status;
	}

	public UserElement() {
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

 	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}


}