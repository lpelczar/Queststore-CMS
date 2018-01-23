package models;


public class User {

    private int id;
    private String name;
    private String login;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;

    public User(int id, String name, String login, String email, String password, String phoneNumber, String role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public String toString(){
        return  "Name: " + name +
                " | Login: " + login +
                " | Email: " + email +
                " | Phone number: " + phoneNumber;
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
}
