package com.talkie.database.model;

import java.util.List;

public class MyFriends {
    private String id;
    private List<UserModel> friends;

    public MyFriends(String id, List<UserModel> friends) {
        this.id = id;
        this.friends = friends;
    }

    public MyFriends() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<UserModel> getFriends() {
        return friends;
    }

    public void setFriends(List<UserModel> friends) {
        this.friends = friends;
    }
}
