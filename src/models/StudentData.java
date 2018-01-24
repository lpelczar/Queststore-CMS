package models;

public class StudentData extends User {

    private String level;
    private String group_name;
    private int exp
    private int balance;



    public Level getLevel() {
        return level;
    }

    public Inventory getBackpack() {
        return backpack;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}