package data.statements;

import data.contracts.StudentItemContract.StudentItemEntry;

public class StudentItemStatement {

    public String getStudentItemsId(int student_id) {
        return "SELECT " + StudentItemEntry.ID_ITEM +
                " FROM " + StudentItemEntry.TABLE_NAME +
                " WHERE " + student_id + " = " + StudeItemEntry.ID_STUDENT + "; ";
    }
}
