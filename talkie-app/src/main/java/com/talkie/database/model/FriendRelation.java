package com.talkie.database.model;

import javax.persistence.*;

@Entity
@Table(name="friends")
public class FriendRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "who")
    private Integer who;
    @Column(name = "with_whom")
    private Integer with;

    private Boolean success;

    public FriendRelation(Integer who, Integer with) {
        this.who = who;
        this.with = with;
    }

    public FriendRelation() {
    }

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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
