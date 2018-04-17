package data;


import org.sqlite.SQLiteConfig;

import java.sql.*;

public class DbHelper {

    private static String DATABASE_PATH = "queststore.db";
    private static final String DRIVER = "org.sqlite.JDBC";
    private Connection connection;

    private void openConnection() {

        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            Class.forName(DRIVER);
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH, config.toProperties());
        } catch ( Exception e ) {
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
        openConnection();
        return connection.prepareStatement(sqlStatement);
    }

    protected ResultSet query(PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }

    public boolean update(PreparedStatement statement) {

        try {
            openConnection();
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }
}