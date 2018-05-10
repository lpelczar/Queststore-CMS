package com.example.queststore.models;

public class Student extends User {

    private StudentData studentData;
    private Group group;

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

    @Override
    public String toString() {
        return super.toString() + " " + studentData.toString() + " " + group.toString();
    }
}
