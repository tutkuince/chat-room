package com.muditasoft.chatroom.model;

import com.alibaba.fastjson.JSON;

import javax.validation.constraints.NotNull;

/**
 * WebSocket message model
 */
public class Message {

    @NotNull(message = "Username cannot be null")
    private String username;
    private int onlineCount;
    private String message;
    private String type;

    public Message(String username, int onlineCount, String message, String type) {
        this.username = username;
        this.onlineCount = onlineCount;
        this.message = message;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
