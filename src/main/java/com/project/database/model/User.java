package com.project.database.model;

import com.project.database.interfaces.SuccessFlaggedObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "users")
public class User extends SuccessFlaggedObject implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "login")
    private String login;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String surname;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "who")
    private List<FriendRelation> friendRelations;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<FriendRelation> getFriendRelations() {
        return friendRelations;
    }

    public void setFriendRelations(List<FriendRelation> friendRelations) {
        this.friendRelations = friendRelations;
    }
}
