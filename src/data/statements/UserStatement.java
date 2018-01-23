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
                user.getRole() + ");" ;
    }
}
