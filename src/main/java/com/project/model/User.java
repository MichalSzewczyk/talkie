package com.project.model;

import com.project.database.interfaces.SuccessFlaggedObject;

public class User implements SuccessFlaggedObject {
    private String login;
    private String password;
    private boolean success;

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, boolean success) {
        this.login = login;
        this.password = password;
        this.success = success;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean getSuccess() {
        return success;
    }
    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && this.login != null && this.login.equals(((User) obj).login);
    }
}
