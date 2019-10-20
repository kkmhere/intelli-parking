package com.example.myapplication.Model;

public class User {
    private String name;
    private String email;
    private  String carNo;
    private String password;

    public User(){

    }

    public User(String name, String email, String carNo, String password) {

        this.name = name;
        this.email = email;
        this.carNo = carNo;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
