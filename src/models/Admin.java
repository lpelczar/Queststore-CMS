package models;

public class Admin extends User {
    private static int generateID = 1;
    private final int adminID;

    public Admin(String name, String login, String password, String email, String phoneNumber) {
        super(name, login, email, password, phoneNumber);
        this.adminID = generateID;
        generateID++;
    }
}
