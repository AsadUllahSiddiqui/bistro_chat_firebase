package com.example.bistro_chat;

public class profile {
    static contact myData;

    profile() {
    }

    public profile(contact myData) {
        this.myData = myData;
    }

    public static contact getMyData() {
        return myData;
    }

    public void setMyData(contact myData) {
        this.myData = myData;
    }
}
