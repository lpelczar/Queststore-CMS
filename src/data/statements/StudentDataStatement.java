package data.statements;

import data.contracts.StudentDataContract.StudentDataEntry;
import data.contracts.StudentItemContract.StudentItemEntry;
import data.contracts.UserContract.UserEntry;
import models.Item;
import models.StudentData;
import models.User;

public class StudentDataStatement {

    public static String getLevelQuery(int student_id) {
        return "SELECT " + StudentDataEntry.LEVEL +
                " FROM " + StudentDataEntry.TABLE_NAME +
                " WHERE " + student_id + " = " + StudentDataEntry.ID_USER + "; ";
    }

    public static String createStudentData(StudentData student, User user) {
        return "INSERT INTO " + StudentDataEntry.TABLE_NAME + " ( " +
                StudentDataEntry.ID_USER + ", " +
                StudentDataEntry.ID_GROUP + ", " +
                StudentDataEntry.TEAM_NAME + ", " +
                StudentDataEntry.LEVEL + ", " +
                StudentDataEntry.BALANCE + ") " +
                "VALUES (" +
                user.getId() + ", " +
                user.getId() + ", \'" +
                student.getTeamName() + "\', \'" +
                student.getLevel() + "\', " +
                student.getBalance() + "); ";
    }

    public static String addItemToBackpack(int student_id, Item item) {
        return "INSERT INTO " + StudentItemEntry.TABLE_NAME + " ( " +
                StudentItemEntry.ID_STUDENT + ", " +
                StudentItemEntry.ID_ITEM + " ) " +
                "VALUES (" +
                student_id  + ", " +
                item.getID() + " ); ";
    }
}
