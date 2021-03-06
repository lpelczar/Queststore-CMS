package com.example.queststore.models;

import java.util.Objects;

public class User {

    private int id;
    private String name;
    private String login;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;

    public User(String name, String login, String email, String password, String phoneNumber, String role) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public User(int id, String name, String login, String email, String password, String phoneNumber, String role) {
        this(name, login, email, password, phoneNumber, role);
        this.id = id;
    }

    public String toString(){
        return  "Name: " + name + " Login: " + login + " Email: " + email + " Phone number: " + phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) { this.password = password; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, login, email, password, phoneNumber, role);
    }
}
