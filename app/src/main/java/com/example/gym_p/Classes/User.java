package com.example.gym_p.Classes;

public class User {

    String email;
    String pass;
    String first_name;
    String last_name;

    public User() {}
    public User(String email, String pass, String first_name, String last_name) {
        this.email = email;
        this.last_name = last_name;
        this.first_name = first_name;
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
