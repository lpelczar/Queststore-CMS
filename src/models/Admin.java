package models;

public class Admin extends User {
    private static int generateID = 1;
    private final int adminID;

    public Admin() {
        super(name, login, email, password, phoneNumber);
        this.adminID = generateID;
        generateID++;
    }
}
