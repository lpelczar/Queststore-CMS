package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.StudentItemContract.StudentItemEntry;

public class StudentItemStatement {

    public String getStudentItemsId(int student_id) {
        return "SELECT " + StudentItemEntry.ID_ITEM +
                " FROM " + StudentItemEntry.TABLE_NAME +
                " WHERE " + student_id + " = " + StudentItemEntry.ID_STUDENT + "; ";
    }
}
