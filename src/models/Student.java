package models;

public class Student extends User {

    private Level level = new Level();
    private Inventory backpack = new Inventory();
    private Integer balance = 0;

    public Student (int id, String name, String login,
                    String email, String password,
                    String phoneNumber, String role) {

        super(id, name, login, email, password, phoneNumber, role);
    }

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