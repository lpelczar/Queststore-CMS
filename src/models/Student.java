package models;

public class StudentData extends User {

    private Level level;
    private Inventory backpack;
    private Integer balance = 0;
    private String group_name;


    public Level getLevel() {
        return level;
    }

    public Inventory getBackpack() {
        return backpack;
    }

    public Integer getBalance() {
        return balance;
    }
}