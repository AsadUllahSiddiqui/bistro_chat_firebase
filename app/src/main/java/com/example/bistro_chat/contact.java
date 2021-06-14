package com.example.bistro_chat;

public class contact {
    private String name;
    private String last_name;
    private String number;
    private String gender;
    private String age;
    private String picture;
    private String id;
    private String email;
    private String bio;
    private String birthday;
    private String msg;
    private String time;

    public contact(String name, String picture, String time, String msg) {
        this.name = name;
        this.number = "";
        this.gender = "";
        this.age = "";
        this.picture = picture;
        this.id = "";
        this.email = "";
        this.last_name = "";
        this.bio = "";
        this.birthday = "";
        this.time = time;
        this.msg = msg;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public contact(String name, String number, String gender, String age, String picture) {
        this.name = name;
        this.number = number;
        this.gender = gender;
        this.age = age;
        this.picture = picture;
        this.id = "";
        this.email = "";
        this.last_name = "";
        this.bio = "";
        this.birthday = "";
        this.time = "";
        this.msg = "";
    }

    public contact(String name, String lastname, String number, String gender, String age,
                   String picture, String id, String email, String bio, String birthday) {
        this.name = name;
        this.number = number;
        this.gender = gender;
        this.age = age;
        this.picture = picture;
        this.id = id;
        this.email = email;
        this.last_name = lastname;
        this.bio = bio;
        this.birthday = birthday;
        this.time = "";
        this.msg = "";

    }

    public contact() {
        this.name = "";
        this.number = "";
        this.gender = "";
        this.age = "";
        this.picture = "";
        this.id = "";
        this.email = "";
        this.last_name = "";
        this.bio = "";
        this.birthday = "";
        this.time = "";
        this.msg = "";
    }

    public contact(String email, String id) {
        this.name = "";
        this.number = "";
        this.gender = "";
        this.age = "";
        this.picture = "";
        this.id = id;
        this.email = email;
        this.last_name = "";
        this.bio = "";
        this.birthday = "";
        this.time = "";
        this.msg = "";
    }


    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
