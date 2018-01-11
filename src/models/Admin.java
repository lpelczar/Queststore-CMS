package models;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    private static int generateID = 1;
    private final int adminID;

    public Admin(String name, String login, String password, String email, String phoneNumber) {
        super(name, login, password, email, phoneNumber);
        this.adminID = generateID;
        generateID++;
    }

    public int getAdminID() {
        return adminID;
    }
}
