package dao;

import java.sql.*;

public class OpenCloseConnectionWithDB {
    protected Connection connection;

    private static String DATABASE_PATH = "queststore.db";
    private static final String DRIVER = "org.sqlite.JDBC";

    public static void setDatabasePath(String path) {
        DATABASE_PATH = path;
    }

    public void openConnection() {

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH);

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public Connection getConnection() {
        openConnection();
        return connection;
    }

    public void closeConnection(Connection connectionToClose) {
        try {
            connectionToClose.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

}

