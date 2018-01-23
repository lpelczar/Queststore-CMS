package data.statements;
import data.contracts.AdminDataContract.AdminDataEntry;
import data.contracts.UserContract.UserEntry;
import models.Admin;

public class AdminDataStatement {


    public String getAll() {
        return "SELECT * FROM " + UserEntry.TABLE_NAME +
                "WHERE " + UserEntry.ROLE + " = admin; ";
    }

    public String add(Admin admin) {
        return "INSERT INTO " + UserEntry.TABLE_NAME + " ( " +
                UserEntry.ID + ", " +
                UserEntry.NAME + ", " +
                UserEntry.LOGIN + ", " +
                UserEntry.PASSWORD + ", " +
                UserEntry.PHONE_NUMBER + ", " +
                UserEntry.ROLE +  ") " +
                "VALUES ( " +
                admin.name + ", " +
                admin.login + ", " +
                admin.password + ", " +
                admin.email + ", " +
                admin.phoneNumber +
                "admin ); ";
    }

    public String update(Admin admin) {}
        return "UPDATE " + UserEntry.TABLE_NAME + " SET " +
                UserEntry.NAME + " = " + admin.getName()
}
