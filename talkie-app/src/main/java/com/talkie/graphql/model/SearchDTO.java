package com.talkie.graphql.model;

import com.talkie.database.model.UserModel;

import java.util.List;

public class SearchDTO {
    private String id;
    private String letters;
    private String length;
    private List<UserModel> users;

    public SearchDTO(String id, String letters, String length, List<UserModel> users) {
        this.id = id;
        this.letters = letters;
        this.length = length;
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
