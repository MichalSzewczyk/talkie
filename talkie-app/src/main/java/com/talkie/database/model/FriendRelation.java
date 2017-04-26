package com.talkie.database.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(FriendRelationPK.class)
@Table(name="friends")
public class FriendRelation {
    @Id
    protected Integer who;
    @Id
    protected Integer withWhom;

    private transient Boolean success;

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
