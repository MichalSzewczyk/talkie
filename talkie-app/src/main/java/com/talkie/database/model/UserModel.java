package com.talkie.database.model;

import com.talkie.dialect.messages.model.User;
import com.talkie.utils.Utils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "login")
    private String login;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "online")
    private Boolean online;
    private transient String success;
    private transient String message;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "friends", joinColumns = @JoinColumn(name = "who"), inverseJoinColumns = @JoinColumn(name = "with_whom"))
    private List<UserModel> friends;

    public UserModel() {
    }

    public UserModel(User user) {
        this.login = user.getLogin();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.avatar = user.getAvatar();
        this.online = user.getOnline();
        this.friends = Utils.convertUsersToUserModel(user.getFriends());
        this.success = user.getSuccess();
        this.message = user.getMessage();
    }

    public UserModel(String login, String name, String lastName, String password, String avatar, Boolean online) {
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.avatar = avatar;
        this.online = online;
    }

    public UserModel(String login, String name, String lastName, String password, String avatar, Boolean online, String success, String message) {
        this(login, name, lastName, password, avatar, online);
        this.success = success;
        this.message = message;
    }

    public UserModel(String success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserModel{" +
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

    public List<UserModel> getFriends() {
        return friends;
    }

    public void setFriends(List<UserModel> friends) {
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
}
