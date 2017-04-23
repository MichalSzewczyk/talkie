package com.talkie.dialect.messages.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private Integer id;
    private String login;
    private String name;
    private String lastName;
    private String password;
    private String avatar;
    private Boolean online;
    private transient String success;
    private transient String message;
    private List<User> friends;
    private String description;
    private String error;

    public User() {
    }

    public User(String login, String name, String lastName, String password, String avatar, Boolean online, String description, String error) {
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.avatar = avatar;
        this.online = online;
        this.description = description;
        this.error = error;
    }

    public User(String login, String name, String lastName, String password, String avatar, Boolean online, String success, String message, String description, String error) {
        this(login, name, lastName, password, avatar, online, description, error);
        this.success = success;
        this.message = message;
    }

    public User(String success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
