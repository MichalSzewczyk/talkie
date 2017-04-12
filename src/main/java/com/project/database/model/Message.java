package com.project.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "messages")
public class Message implements Serializable {
    @Column(name = "sender")
    private Integer senderId;

    @Column(name = "receiver")
    private Integer receiverId;

    @Id
    @Column(name = "message_timestamp")
    private Long timestamp;

    @Column(name = "message")
    private String message;

    public Message() {

    }

    public Message(Integer senderId, Integer receiverId, Long timestamp, String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.timestamp = timestamp;
        this.message = message;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
