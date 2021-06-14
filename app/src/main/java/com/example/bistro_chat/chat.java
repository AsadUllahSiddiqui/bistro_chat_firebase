package com.example.bistro_chat;

public class chat {
    private String name;
    private String msg;
    private String picture;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

    public chat(String name, String msg, String picture, String time) {
        this.name = name;
        this.msg = msg;
        this.picture = picture;
        this.time = time;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}


