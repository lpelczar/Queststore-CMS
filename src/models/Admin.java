package models;

public class Admin extends User {
    int generateID = 0;
    int adminID;

    Admin(){
        generateID ++;
        this.adminID = generateID;
    }
}
