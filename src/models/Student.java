package models;

public class Student extends User {

    Level level = new Level();
    Inventory backpack = new Inventory();
    Integer balance = 0;

    public Student (int id, String name, String login,
                    String email, String password,
                    String phoneNumber, String role) {

        super(id, name, login, email, password, phoneNumber, role);
    }
}