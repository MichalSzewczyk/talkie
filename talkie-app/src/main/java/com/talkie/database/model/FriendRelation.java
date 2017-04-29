package com.talkie.database.model;

import javax.persistence.*;

@Entity
@IdClass(FriendRelationPK.class)
@Table(name = "friends")
public class FriendRelation {
    @Id
    protected Integer who;
    @Id
    protected Integer withWhom;

    @Transient
    private Boolean success;

    public FriendRelation(Integer who, Integer withWhom) {
        this.who = who;
        this.withWhom = withWhom;
    }

    public FriendRelation() {
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
