package models;

public class Student extends User {
    super(name, email, login, password, phoneNumber);
    private Inventory backpack;
    private Integer levelGrade;
    private Integer coolCoins;

    Student(Integer coolCoins) {
        this.backpack = Inventory();
        this.levelGrade = 1;
        this.coolCoins = coolCoins;
    }

    public void getTaskBy(String name) {
        ; // Student need to see and find tasks
    }
}
