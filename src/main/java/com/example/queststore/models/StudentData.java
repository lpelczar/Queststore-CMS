package com.example.queststore.models;

import java.util.Objects;

public class StudentData {

    private int studentId;
    private int groupId;
    private String teamName;
    private String level;
    private int balance;
    private int experience;

    public StudentData() {
        this.level = "Beginner";
        this.groupId = 1;
        this.teamName = "Not assigned";
        this.balance = 0;
        this.experience = 0;
    }

    public StudentData(int studentId, Integer groupId, String teamName, String level, int balance, int experience) {
        this.studentId = studentId;
        this.groupId = groupId;
        this.teamName = teamName;
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

    public Integer getGroupName() { return groupId; }

    public String getTeamName() { return teamName; }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setBalance(int balance) { this.balance = balance; }

    public int getExperience() { return experience; }

    public int getId() { return studentId; }

    public Integer getGroupId() { return groupId; }

    public void setStudentId(int studentId) { this.studentId = studentId; }

    public void setTeamName(String team_name) { this.teamName = team_name; }

    public void setGroupId(int group_id) {
        this.groupId = group_id;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String toString() {
        return "Student Id: " + studentId + "\n" +
                "Group Id: " + groupId + "\n" +
                "Team name: " + teamName + "\n" +
                "Level: " + level + "\n" +
                "Balance: " + balance + "\n" +
                "Exp: " + experience;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentData that = (StudentData) o;
        return studentId == that.studentId &&
                groupId == that.groupId &&
                balance == that.balance &&
                experience == that.experience &&
                Objects.equals(teamName, that.teamName) &&
                Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, groupId, teamName, level, balance, experience);
    }
}