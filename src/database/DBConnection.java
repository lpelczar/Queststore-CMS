import java.sql.*;

public class DBConnection {

    public Connection openConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        }
        catch (ClassNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        Connection connection = DriverManager.getConnection("jdbc:sqlite:data/NAZWABAZYDANYCH.db");
        connection.setAutoCommit(false);

        return connection;
    }

    public Statement setUpStatement(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement;
    }

    public void closeConnection(Statement statement, Connection connection) throws SQLException {

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("Request hasn\'t been closed properly.");
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Connection hasn\'t been close properly.");
            }
        }
    }
}