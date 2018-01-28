package models;

public class StudentData {

    private String level;
    private String group_name;
    private String team_name;
    private int balance;
    private int exp;

    public StudentData() {
        this.level = "beginner";
        this.group_name = "Not assign yet.";
        this.team_name = "Not assign yet.";
        this.balance = 0;
        this.exp = 0;
    }

    public StudentData(String level, String group_name, String team_name, int balance, int exp) {
        this.level = level;
        this.group_name = group_name;
        this.team_name = team_name;
        this.balance = balance;
        this.exp = exp;
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

    public void setBalance(int balance) { this.balance = balance; }
}