package com.talkie.dialect.payloads;

public class UserElement {
    private Integer id;
    private String status;

    public UserElement(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public UserElement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}