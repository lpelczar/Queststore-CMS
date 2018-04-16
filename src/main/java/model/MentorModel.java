package model;


public class MentorModel extends UserModel{

    public MentorModel(String id, String login, String password, String name, String lastName) {
        super(id, login, password, name, lastName);
    }

    public MentorModel(String id, String login, String password, String name, String lastName, String email) {
        super(id, login, password, name, lastName, email);
    }
  
    public MentorModel(String login, String password, String name, String lastName) {
        super(login, password, name, lastName);
    }
}

