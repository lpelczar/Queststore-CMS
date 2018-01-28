package data.statements;

import data.contracts.StudentDataContract.StudentDataEntry;
import data.contracts.StudentItemContract.StudentItemEntry;
import data.contracts.UserContract.UserEntry;
import models.Item;
import models.StudentData;
import models.User;

public class StudentDataStatement {

    public static String getStudentData(int student_id) {
        return "SELECT * FROM " + StudentDataEntry.TABLE_NAME +
                " WHERE " + student_id + " = " + StudentDataEntry.ID_USER + "; ";
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
