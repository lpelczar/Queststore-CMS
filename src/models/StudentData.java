package models;

public class StudentData {

    private String level;
    private String group_name;
    private String team_name;
    private int exp;
    private int balance;

    public StudentData() {
        this.level = "beginner";
        this.group_name = "Not assign yet.";
        this.team_name = "Not assign yet.";
        this.exp = 0;
        this.balance = 0;
    }

    public String getLevel() {
        return level;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getGroupName() { return group_name; }

    public String getTeamName() { return team_name; }

    public void setLevel(String level) {
        this.level = level;
    }
}