package utils;

import org.sqlite.SQLiteConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DatabaseCreator {

    private static String DATABASE_PATH = "queststore.db";
    private static final String DRIVER = "org.sqlite.JDBC";
    private Connection connection;

    public static void setDatabasePath(String path) {
        DATABASE_PATH = path;
    }

    public void createDatabaseFromScript(String scriptPath) {
        String fileContent = "";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(scriptPath)));
        } catch (IOException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        List<String> statements = Arrays.asList(fileContent.split(";"));
        try {
            openConnection();
            for (String statement : statements) {
                getPreparedStatement(statement.trim()).executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            closeConnection();
        }
    }

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

    private void closeConnection() {

        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) { /*ignored*/ }
    }

    private PreparedStatement getPreparedStatement(String sqlStatement) throws SQLException {
        openConnection();
        return connection.prepareStatement(sqlStatement);
    }
}
