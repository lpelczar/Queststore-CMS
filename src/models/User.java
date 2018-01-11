package models;

import java.io.Serializable;

public abstract class User implements Serializable{
    private String name;
    private String login;
    private String password;
    private String email;
    private String phoneNumber;

    User(String name, String login, String password, String email, String phoneNumber) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String toString(){
        return  "Name: " + name +
                " Login: " + login +
                " Email: " + email +
                " Phone number: " + phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
