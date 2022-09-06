package com.example.firebase.Model;

public class Data {
    private String Username,Email,pass,id;
    public Data()
    {

    }

    public Data(String username, String email, String pass, String id) {
        Username = username;
        Email = email;
        this.pass = pass;
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
