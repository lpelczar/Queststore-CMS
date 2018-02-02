package main.java.com.example.queststore.data;


import org.sqlite.SQLiteConfig;

import java.sql.*;

public class DbHelper {

    private static final String DB_URL = "jdbc:sqlite:queststore.db";
    private static final String DRIVER = "org.sqlite.JDBC";
    private Connection connection;
    private Statement statement;

    private void openConnection() {

        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, config.toProperties());
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    protected void closeConnection() {

        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) { /*ignored*/ }
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) { /*ignored*/ }
    }

    protected ResultSet query(String sqlStatement) throws SQLException {

        openConnection();
        statement = connection.createStatement();
        return statement.executeQuery(sqlStatement);
    }

    public boolean update(String sqlStatement) {

        try {
            openConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(sqlStatement);
            connection.commit();
            return true;
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        } finally {
            closeConnection();
        }
        return false;
    }
}