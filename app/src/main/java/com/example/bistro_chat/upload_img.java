package com.example.bistro_chat;

public class upload_img {
    private String id;
    private String url;

    public upload_img(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public upload_img() {
        this.id = "";
        this.url = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
