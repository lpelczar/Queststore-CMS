package com.example.queststore.data;


import com.example.queststore.data.statements.UserStatement;
import com.example.queststore.utils.QueryLogger;
import org.sqlite.SQLiteConfig;

import java.io.File;
import java.sql.*;

public class DbHelper {

    private static final String DATABASE_PATH = "queststore.db";
    private static final String DB_URL = "jdbc:sqlite:queststore.db";
    private static final String DRIVER = "org.sqlite.JDBC";
    private Connection connection;

    public boolean isDatabaseFileExists() {
        return new File(DATABASE_PATH).isFile();
    }

    public void createDatabase() {
        String createUsersTable = new UserStatement().createTable();

        try {
            openConnection();
            getPreparedStatement(createUsersTable).executeUpdate();
        } catch (SQLException e) {
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private void openConnection() {

        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, config.toProperties());
        } catch ( Exception e ) {
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    protected void closeConnection() {

        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) { /*ignored*/ }
    }

    protected PreparedStatement getPreparedStatement(String sqlStatement) throws SQLException {
        QueryLogger.logInfo(sqlStatement, "logs/queries.log");
        openConnection();
        return connection.prepareStatement(sqlStatement);
    }

    protected ResultSet query(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    public boolean update(PreparedStatement statement) {

        try {
            openConnection();
            connection.setAutoCommit(false);
            statement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            QueryLogger.logInfo(e.getClass().getName() + ": " + e.getMessage(), "logs/errors.log");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }
}