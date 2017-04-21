package com.talkie.database.model;

import javax.persistence.*;

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
}
