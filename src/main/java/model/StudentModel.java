package model;


public class StudentModel extends UserModel {

    public StudentModel(String id, String login, String password, String name,
                        String lastName, String email) {
        super(id, login, password, name, lastName, email);
    }

    public StudentModel(String id, String login, String password, String name, String lastName){
        super(id, login, password, name, lastName);
    }
}