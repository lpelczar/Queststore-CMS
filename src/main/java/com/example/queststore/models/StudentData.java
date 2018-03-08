package com.example.queststore.models;

import java.util.Objects;

public class StudentData {

    private int student_id;
    private int group_id;
    private String team_name;
    private String level;
    private int balance;
    private int experience;

    public StudentData() {
        this.level = "Beginner";
        this.group_id = 1;
        this.team_name = "Not assigned";
        this.balance = 0;
        this.experience = 0;
    }

    public StudentData(int student_id, Integer group_id, String team_name, String level, int balance, int experience) {
        this.student_id = student_id;
        this.group_id = group_id;
        this.team_name = team_name;
        this.level = level;
        this.balance = balance;
        this.experience = experience;
    }

    public String getLevel() {
        return level;
    }

    public Integer getBalance() {
        return balance;
    }

    public Integer getGroupName() { return group_id; }

    public String getTeamName() { return team_name; }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setBalance(int balance) { this.balance = balance; }

    public int getExperience() { return experience; }

    public int getId() { return student_id; }

    public Integer getGroupId() { return group_id; }

    public void setStudentId(int studentId) { this.student_id = studentId; }

    public void setTeamName(String team_name) { this.team_name = team_name; }

    public void setGroupId(int group_id) {
        this.group_id = group_id;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String toString() {
        return "Student Id: " + student_id + "\n" +
                "Group Id: " + group_id + "\n" +
                "Team name: " + team_name + "\n" +
                "Level: " + level + "\n" +
                "Balance: " + balance + "\n" +
                "Exp: " + experience;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentData that = (StudentData) o;
        return student_id == that.student_id &&
                group_id == that.group_id &&
                balance == that.balance &&
                experience == that.experience &&
                Objects.equals(team_name, that.team_name) &&
                Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {

        return Objects.hash(student_id, group_id, team_name, level, balance, experience);
    }
}