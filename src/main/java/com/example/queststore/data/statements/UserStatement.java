package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.StudentDataEntry;
import com.example.queststore.data.contracts.UserEntry;

public class UserStatement {

    public String selectAllUsers() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME + ";" ;
    }

    public String selectUserById() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.ID + " = ?;" ;
    }

    public String insertUserStatement() {
        return "INSERT INTO " + UserEntry.TABLE_NAME + " (" +
                UserEntry.NAME + "," +
                UserEntry.LOGIN + "," +
                UserEntry.EMAIL + "," +
                UserEntry.PASSWORD + "," +
                UserEntry.PHONE_NUMBER + "," +
                UserEntry.ROLE + ")" +
                " VALUES (?,?,?,?,?,?);" ;
    }

    public String updateUserStatement() {
        return "UPDATE " + UserEntry.TABLE_NAME + " SET " +
        UserEntry.NAME + " = ?," +
        UserEntry.LOGIN + " = ?," +
        UserEntry.EMAIL + " = ?," +
        UserEntry.PASSWORD + " = ?," +
        UserEntry.PHONE_NUMBER + " = ?," +
        UserEntry.ROLE + " = ? WHERE " + UserEntry.ID + " = ?;";
    }

    public String deleteUserStatement() {
        return "DELETE FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.ID + " = ?;" ;
    }

    public String selectUserByLoginAndPassword() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.LOGIN + " = ? AND " +
                UserEntry.PASSWORD + " = ?;" ;
    }

    public String selectUserByLoginAndRole() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.LOGIN + " = ? AND " +
                UserEntry.ROLE + " = ?;";
    }

    public String selectUserByLogin() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.LOGIN + " = ?;";
    }

    public String selectAllUsersByRole() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.ROLE + " = ?;";
    }

    public String selectUserByEmail() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.EMAIL + " = ?;";
    }

    public String selectUserByPhoneNumber() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.PHONE_NUMBER + " = ?;";
    }

    public String selectAllStudentsByGroupId() {
        return "SELECT " +
                UserEntry.ID + "," +
                UserEntry.NAME + "," +
                UserEntry.LOGIN + "," +
                UserEntry.EMAIL + "," +
                UserEntry.PASSWORD + "," +
                UserEntry.PHONE_NUMBER + "," +
                UserEntry.ROLE +
                " FROM " + UserEntry.TABLE_NAME +
                " JOIN " + StudentDataEntry.TABLE_NAME + " ON " + UserEntry.TABLE_NAME + "." + UserEntry.ID + " = " +
                StudentDataEntry.TABLE_NAME + "." + StudentDataEntry.ID_USER +
                " WHERE " + StudentDataEntry.TABLE_NAME + "." + StudentDataEntry.ID_GROUP + " = ?;";
    }
}
