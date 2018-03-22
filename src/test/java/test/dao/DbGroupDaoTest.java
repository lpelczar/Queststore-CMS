package test.dao;

import org.junit.jupiter.api.*;
import com.example.queststore.data.DbHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class DbGroupDaoTest {

    private static String path = "test.db";

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(new File(DbHelper.getDatabasePath()).toPath());
    }

    @BeforeEach
    void dbSetup() throws IOException {
        DbHelper.setDatabasePath("test.db");

        Files.deleteIfExists(new File(DbHelper.getDatabasePath()).toPath());
        DbHelper dbHelper = new DbHelper();
        dbHelper.createDatabase();
    }

    @Test
    @DisplayName("Create Fake Database Test") // using runSqlScriptsFromFile() from DbHelper
    void createFakeDatabaseTest() {

        boolean fileFound = true;

        try {
            new FileInputStream(new File(DbGroupDaoTest.path));
        }catch(FileNotFoundException fnfe) {
            fileFound = false;
        }

        assertTrue(fileFound);
    }
}
