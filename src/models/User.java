package models;

public abstract class User {
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
