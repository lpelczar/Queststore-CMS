package models;

import java.io.Serializable;

public class Student extends User implements Serializable {
    private Inventory backpack;
    private Integer levelGrade;
    private Integer coolCoins;

    Student(String name, String login, String password, String email, String phoneNumber, Integer coolCoins) {
        super(name, email, login, password, phoneNumber);
        this.backpack = new Inventory();
        this.levelGrade = 1;
        this.coolCoins = coolCoins;
    }

    public void getTaskBy(String name) {
        ; // Student need to see and find tasks
    }
}
