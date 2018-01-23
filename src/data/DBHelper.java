package data;

import java.sql.*;

class DbHelper {

    private static final String DATABASE_NAME = "queststore.db";
    private Connection connection;
    private Statement statement;

    private void openConnection() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    void closeConnection() {

        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) { /*ignored*/ }
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) { /*ignored*/ }
    }
}