package data.statements;


import data.contracts.UserContract.UserEntry;
import models.User;

public class UserStatement {

    public String selectAllUsers() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME + ";" ;
    }

    public String selectUserById(int id) {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.ID + " = " + id + ";" ;
    }

    public String insertUserStatement(User user) {
        return "INSERT INTO " + UserEntry.TABLE_NAME + " (" +
                UserEntry.ID + "," +
                UserEntry.NAME + "," +
                UserEntry.LOGIN + "," +
                UserEntry.EMAIL + "," +
                UserEntry.PASSWORD + "," +
                UserEntry.PHONE_NUMBER + "," +
                UserEntry.ROLE + ")" +
                " VALUES (" +
                "'" + user.getId() + "'," +
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

    public String selectUserByLoginAndPassword(String login, String password) {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.LOGIN + " = '" + login + "' AND " +
                UserEntry.PASSWORD + " = '" + password + "';" ;
    }

    public String selectUserByLogin(String login) {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                " WHERE " + UserEntry.LOGIN + " = '" + login + "';";
    }
}
