package models;

public abstract class User {
    private String name;
    private String email;
    private String login;
    private String password;
    private String phoneNumber;

    public String toString(){
        return "";
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
}
