package com.project.database.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="friends")
public class FriendRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "who")
    private Integer who;
    @Column(name = "with")
    private Integer with;
    @OneToMany
    @JoinColumn(name="with", referencedColumnName = "id")
    private List<User> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWho() {
        return who;
    }

    public void setWho(Integer who) {
        this.who = who;
    }

    public Integer getWith() {
        return with;
    }

    public void setWith(Integer with) {
        this.with = with;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
