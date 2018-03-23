package test.dao;

import com.example.queststore.models.Group;
import com.example.queststore.dao.DbGroupDAO;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DbGroupDaoTest {

    private static String DATABASE_PATH = "test.db";
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String TEST_DATA = "InsertFakeData.sql";

    @BeforeEach
    void resetDB() throws SQLException, ClassNotFoundException, FileNotFoundException{

        Connection connection;
        Statement statement;

        Class.forName(DRIVER);
        connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH);

        statement = connection.createStatement();
        String query = "";

        query += "DROP TABLE IF EXISTS `mentors_groups` ; ";
        query += "DROP TABLE IF EXISTS `students_data` ; ";
        query += "DROP TABLE IF EXISTS `students_items` ; ";
        query += "DROP TABLE IF EXISTS `students_tasks` ; ";
        query += "DROP TABLE IF EXISTS `experience_levels` ; ";
        query += "DROP TABLE IF EXISTS `groups` ; ";
        query += "DROP TABLE IF EXISTS `items` ; ";
        query += "DROP TABLE IF EXISTS `tasks` ; ";
        query += "DROP TABLE IF EXISTS `users` ; ";

        statement.executeUpdate(query);

        query = new Scanner(new File(TEST_DATA)).useDelimiter("\\Z").next();
        statement.executeUpdate(query);

        connection.close();

    }

    @AfterAll
    static void clearDB() throws SQLException, ClassNotFoundException, FileNotFoundException{
        DbGroupDaoTest test = new DbGroupDaoTest();
        test.resetDB();
    }

    @Test
    @DisplayName("Create Fake Database Test") // using runSqlScriptsFromFile() from DbHelper
    @Disabled("Create Fake Database Test is disabled. Reason: only for testing environment and checking if @Before and @After work")
    void createFakeDatabaseTest() {

        boolean fileFound = true;

        try {
            new FileInputStream(new File(DbGroupDaoTest.DATABASE_PATH));
        }catch(FileNotFoundException fe) {
            fileFound = false;
        }

        assertTrue(fileFound);
    }

    @Test
    @DisplayName("Test get by name with existing name")
    void getByNameTest(){

        DbGroupDAO dao = new DbGroupDAO();
        String name = "Masters";
        Group testGroup = dao.getByName(name);
        assertEquals(testGroup.getGroupName(), name);
    }

    @Test
    @DisplayName("Test get by name with non-existent name")
    void getByNonexistingNameTest(){

        DbGroupDAO dao = new DbGroupDAO();
        String name = "Non-existent name";
        Group testGroup = dao.getByName(name);
        assertNull(testGroup);
    }

    @Test
    @DisplayName("Test add group which is null")
    void addNullGroupTest(){
        DbGroupDAO dao = new DbGroupDAO();
        Group testGroup = mock(Group.class);
        assertFalse(dao.add(testGroup));
    }

    @Test
    @DisplayName("Test add group which is not null")
    void addNotNullGroup(){
        DbGroupDAO dao = new DbGroupDAO();
        String name = "Test opopppoGroup";
        Group testGroup = new Group(name);
        assertTrue(dao.add(testGroup));
    }

    @Test
    @DisplayName("Test if group added properly")
    void addProperlyTest(){
        DbGroupDAO dao = new DbGroupDAO();
        String name = "Test Group";
        Group testGroup = new Group(name);
        dao.add(testGroup);
        assertEquals(dao.getByName(name).getGroupName() , name);
    }

    @Test
    @DisplayName("Test if existing group can be added")
    void addExistingGroup(){
        DbGroupDAO dao = new DbGroupDAO();
        String name = "Test Group";
        Group testGroup = new Group(name);
        dao.add(testGroup);
        assertFalse(dao.add(testGroup));
    }

    @Test
    @DisplayName("Test if delete null possible")
    void deleteNullTest(){
        DbGroupDAO dao = new DbGroupDAO();
        Group testGroup = null;
        assertFalse(dao.delete(testGroup));
    }

    @Test
    @DisplayName("Test if any group can be removed")
    void removeMockTest(){
        DbGroupDAO dao = new DbGroupDAO();
        Group testGroup = mock(Group.class);
        assertTrue(dao.delete(testGroup));
    }

    @Test
    @DisplayName("Test if group removed properly")
    void properRemovalTest(){
        DbGroupDAO dao = new DbGroupDAO();
        String name = "Test Group";
        Group testGroup = new Group(name);
        dao.add(testGroup);
        dao.delete(testGroup);
        assertNull(dao.getByName(name));

    }
}
