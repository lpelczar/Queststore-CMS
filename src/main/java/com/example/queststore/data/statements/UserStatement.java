package com.example.queststore.data.statements;


import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.User;

public class UserStatement {

    public String selectAllUsers() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME + ";" ;
    }

    public String selectUserById() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.ID + " = ?;" ;
    }

    public String insertUserStatement(User user) {
        return "INSERT INTO " + UserEntry.TABLE_NAME + " (" +
                UserEntry.NAME + "," +
                UserEntry.LOGIN + "," +
                UserEntry.EMAIL + "," +
                UserEntry.PASSWORD + "," +
                UserEntry.PHONE_NUMBER + "," +
                UserEntry.ROLE + ")" +
                " VALUES (" +
                "'" + user.getName() + "'," +
                "'" + user.getLogin() + "'," +
                "'" + user.getEmail() + "'," +
                "'" + user.getPassword() + "'," +
                "'" + user.getPhoneNumber() + "'," +
                "'" + user.getRole() + "');" ;
    }

    public String updateUserStatement(User user) {
        return "UPDATE " + UserEntry.TABLE_NAME + " SET " +
        UserEntry.NAME + " = '" + user.getName() + "'," +
        UserEntry.LOGIN + " = '" + user.getLogin() + "'," +
        UserEntry.EMAIL + " = '" + user.getEmail() + "'," +
        UserEntry.PASSWORD + " = '" + user.getPassword() + "'," +
        UserEntry.PHONE_NUMBER + " = '" + user.getPhoneNumber() + "'," +
        UserEntry.ROLE + " = '" + user.getRole() +
        "' WHERE " + UserEntry.ID + " = " + user.getId() + ";";
    }

    public String deleteUserStatement(User user) {
        return "DELETE FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.ID + " = " + user.getId() + ";" ;
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
}
