package com.talkie.graphql.model;

import com.talkie.database.model.UserModel;

import java.util.List;

public class SearchDTO {
    private String id;
    private String letters;
    private String length;
    private List<UserModel> friends;

    public SearchDTO(String id, String letters, String length, List<UserModel> friends) {
        this.id = id;
        this.letters = letters;
        this.length = length;
        this.friends = friends;
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

    public List<UserModel> getFriends() {
        return friends;
    }

    public void setFriends(List<UserModel> friends) {
        this.friends = friends;
    }
}
