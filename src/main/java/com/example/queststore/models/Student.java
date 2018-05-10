package com.example.queststore.models;

import java.util.List;

public class Student extends User {

    private StudentData studentData;
    private Group group;
    private List<Item> boughtItems;
    private List<Task> doneTasks;

    public Student(User user) {
        super(user.getId(), user.getName(), user.getLogin(), user.getEmail(), user.getPassword(), user.getPhoneNumber(),
                user.getRole());
    }

    public StudentData getStudentData() {
        return studentData;
    }

    public void setStudentData(StudentData studentData) {
        this.studentData = studentData;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Item> getBoughtItems() {
        return boughtItems;
    }

    public void setBoughtItems(List<Item> boughtItems) {
        this.boughtItems = boughtItems;
    }

    public List<Task> getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(List<Task> doneTasks) {
        this.doneTasks = doneTasks;
    }

    @Override
    public String toString() {
        return super.toString() + " " + studentData.toString() + " " + group.toString()
                + " " + boughtItems.toString() + " " + doneTasks.toString();
    }
}
