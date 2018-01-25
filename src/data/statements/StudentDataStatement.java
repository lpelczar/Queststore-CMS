package data.statements;

import data.contracts.StudentDataContract.StudentDataEntry;

public class StudentDataStatement {

    public static String getLevelQuery(int student_id) {
        return "SELECT " + StudentDataEntry.LEVEL +
                " FROM " + StudentDataEntry.TABLE_NAME +
                " WHERE " + student_id + " = " + StudentDataEntry.ID_USER + "; ";
    }
}
