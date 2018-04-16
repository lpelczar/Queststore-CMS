package dao;

import utils.ProcessManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueriesGenerator {

    private ProcessManager processManager = new ProcessManager();

    public PreparedStatement getFullDataOfUser(String tableName, String idColumnName, int id) {

        String statement =
                "SELECT user_id, login, password, name, lastname, email " +
                " FROM logins " +
                " INNER JOIN " + tableName + " ON logins.user_id = " + tableName + "." + idColumnName +
                " WHERE user_id = ?;";

        return processManager.getPreparedStatement(statement, String.valueOf(id));
    }

    public PreparedStatement getMentorGroup(int mentor_id) {

        String statement = "SELECT signature, groups.group_name_id " +
                "FROM group_names " +
                "JOIN groups " +
                "ON groups.group_name_id = group_names.group_name_id " +
                "WHERE mentor_id = ?;";

        return processManager.getPreparedStatement(statement, String.valueOf(mentor_id));
    }

    public PreparedStatement getStudentsIdsFromExactGroup(int group_id) {
        String statement = "SELECT student_id FROM groups WHERE group_name_id = ?";

        return processManager.getPreparedStatement(statement, String.valueOf(group_id));

    }



    public PreparedStatement getFullDataOfAllUsers(String tableName, String idColumnName, int role) {

        String statement =
                "SELECT user_id, login, password," +
                        tableName + ".name, " +
                        tableName + ".lastname, " +
                        tableName + ".email " +
                        "FROM logins " +

                        "INNER JOIN " + tableName + " ON " +
                        "logins.user_id = " + tableName + "." + idColumnName  +
                        " WHERE role = ? ;";

        return processManager.getPreparedStatement(statement, String.valueOf(role));
    }

    public PreparedStatement updateLoginDataOfUser(String newLogin,
                                                   String newPassword, int newId) {
        String statement =
                "UPDATE logins"
                + " SET login=?"
                + ", password=?"
                + " WHERE user_id=?;";

        return processManager.getPreparedStatement(statement, newLogin, newPassword, String.valueOf(newId));
    }

    public PreparedStatement updatePersonalDataOfUser(String tableName,
                                                      String columnName, String newName, String newLastname,
                                                      String newEmail, int newId) {
        String statement =
                            "UPDATE "
                                 + tableName
                                 + " SET name=?"
                                 + ", lastname=?"
                                 + ", email=?"
                                 + " WHERE "
                                 + columnName
                                 + "=?;";
    //            statement.setString(1, tableName);

        return processManager.getPreparedStatement(statement, newName, newLastname, newEmail, String.valueOf(newId));
    }

    public PreparedStatement getAllQuests(String tableName) {
       String statement = "SELECT * FROM " + tableName + ";";
       return processManager.getPreparedStatement(statement);

    }

    public PreparedStatement getQuestById(int id) {
        String statement = "SELECT * FROM quests WHERE quest_id = ?;";
        return processManager.getPreparedStatement(statement, Integer.toString(id));

    }

    public PreparedStatement insertItem(String tableName, int quest_id, String name, String description, int price) {
        String statement = "INSERT INTO "
            + tableName
            + " (quest_id, name, description, price) VALUES(?, ?, ?, ?);";

        return processManager.getPreparedStatement(statement, String.valueOf(quest_id), name, description, String.valueOf(price));
    }

    public PreparedStatement getUserItems(String itemTableName, String idColumnName, String studentItemsTableName, int id) {
        String statement = "SELECT "
                + "artefacts.artefact_id, artefacts.name, artefacts.description, artefacts.price, status.status_name"
                + " FROM "
                + itemTableName
                + " INNER JOIN " + studentItemsTableName +" ON "
                + itemTableName + "." + idColumnName + "=" + studentItemsTableName + "." + idColumnName
                + " INNER JOIN status ON status.status_id = " + studentItemsTableName + ".status_id"
                + " WHERE student_id =?;";

        return processManager.getPreparedStatement(statement, String.valueOf(id));
    }

    public PreparedStatement getUserWallet(int id) {
        String statement = "SELECT student_id, balance, total_earned FROM students_wallets WHERE student_id=?;";

        return processManager.getPreparedStatement(statement, String.valueOf(id));
    }



    public PreparedStatement updateItem(String tableName, int quest_id, String name, String description, int price) {
        String statement = "UPDATE "
            + tableName
            + " SET name = ?, description = ?, price = ? WHERE quest_id = ?;" ;

        return processManager.getPreparedStatement(statement, name, description, String.valueOf(price), String.valueOf(quest_id));

    }


    public PreparedStatement deleteItem(String tableName, int quest_id) {
        String statement = "DELETE FROM " + tableName + " WHERE quest_id = ?;";

        return processManager.getPreparedStatement(statement, String.valueOf(quest_id));

    }

}
