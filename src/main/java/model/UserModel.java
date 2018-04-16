package model;


import java.util.Objects;

public abstract class UserModel {

    protected String id;
    protected String login;
    protected String password;
    protected String name;
    protected String lastName;
    protected String email;

    protected UserModel(String id, String login, String password, String name, String lastName) {
        int newId = Integer.parseInt(id);
        String finalId = Integer.valueOf(++newId).toString();
        this.id = finalId;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = createEmail();
    }


    protected UserModel(String id, String login, String password, String name, String lastName, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        
    }

    UserModel(String login, String password, String name, String lastName) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }

    protected String createEmail(){
        return String.format("%s.%s@codecool.com", name, lastName);
    }

    public String getLogin(){
        return this.login; 
    }

    public String getPassword(){
        return this.password;
    }

    public String getName(){
        return this.name;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getEmail(){
        return this.email;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    public String toString(){
        return String.format("Id.: %s\nPassword: %s\nLogin: %s\nName: %s\nLast name: %s\nEmail: %s",
                             id, password, login, name, lastName, email);
    }

    public String getId(){
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) &&
                Objects.equals(login, userModel.login) &&
                Objects.equals(password, userModel.password) &&
                Objects.equals(name, userModel.name) &&
                Objects.equals(lastName, userModel.lastName) &&
                Objects.equals(email, userModel.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, login, password, name, lastName, email);
    }
}