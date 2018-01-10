package models;

public class Admin extends User {
    int generateID = 0;
    int adminID;

    public Admin() {
        super(name, login, email, password, phoneNumber);
        generateID ++;
        this.adminID = generateID;
    }
}
