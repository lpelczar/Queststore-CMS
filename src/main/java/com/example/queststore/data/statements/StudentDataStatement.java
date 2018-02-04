package com.example.queststore.data.statements;

import com.example.queststore.data.contracts.StudentDataEntry;
import com.example.queststore.data.contracts.StudentItemEntry;
import com.example.queststore.models.Item;
import com.example.queststore.models.StudentData;

public class StudentDataStatement {

    public String getStudentDataById() {
        return "SELECT * FROM " + StudentDataEntry.TABLE_NAME +
                " WHERE " + StudentDataEntry.ID_USER + " = ?; ";
    }

    public static String createStudentData(StudentData student) {
        return "INSERT INTO " + StudentDataEntry.TABLE_NAME + " ( " +
                StudentDataEntry.ID_USER + ", " +
                StudentDataEntry.ID_GROUP + ", " +
                StudentDataEntry.TEAM_NAME + ", " +
                StudentDataEntry.LEVEL + ", " +
                StudentDataEntry.BALANCE + ", " +
                StudentDataEntry.EXPERIENCE + ") " +
                "VALUES (" +
                student.getId() + ", " +
                student.getId() + ", \'" +
                student.getTeamName() + "\', \'" +
                student.getLevel() + "\', " +
                student.getBalance() + ", " +
                student.getExperience() + "); ";
    }

    public static String addItemToBackpack(int student_id, Item item) {
        return "INSERT INTO " + StudentItemEntry.TABLE_NAME + " ( " +
                StudentItemEntry.ID_STUDENT + ", " +
                StudentItemEntry.ID_ITEM + " ) " +
                "VALUES (" +
                student_id  + ", " +
                item.getID() + " ); ";
    }

    public static String updateStudentData(StudentData student) {
        return "UPDATE " + StudentDataEntry.TABLE_NAME + " SET " +
                StudentDataEntry.ID_GROUP + " = " + student.getGroupId() + ", " +
                StudentDataEntry.TEAM_NAME + " = " + student.getTeamName() + ", " +
                StudentDataEntry.LEVEL + " = " + student.getLevel() + ", " +
                StudentDataEntry.BALANCE + " = " + student.getBalance() + ", " +
                StudentDataEntry.EXPERIENCE + " = " + student.getExperience() +
                " WHERE " + StudentDataEntry.ID_USER + " = " + student.getId() + "; ";
    }
}
