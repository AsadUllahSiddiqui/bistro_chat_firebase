package com.example.bistro_chat;

public class message {
    private String sender, receiver, msg, time;

    public message(String sender, String receiver, String msg, String time) {
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
        this.time = time;
    }

    public message() {
        this.sender = "";
        this.receiver = "";
        this.msg = "";
        this.time = "";
    }

    public message(String sender, String receiver, String msg) {
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
        this.time = "";
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
