package com.talkie.database.model;

import java.io.Serializable;

public class FriendRelationPK implements Serializable {
    private Integer who;
    private Integer withWhom;

    public FriendRelationPK() {
    }

    public FriendRelationPK(Integer who, Integer withWhom) {
        this.who = who;
        this.withWhom = withWhom;
    }

    public Integer getWho() {
        return who;
    }

    public void setWho(Integer who) {
        this.who = who;
    }

    public Integer getWithWhom() {
        return withWhom;
    }

    public void setWithWhom(Integer withWhom) {
        this.withWhom = withWhom;
    }
}