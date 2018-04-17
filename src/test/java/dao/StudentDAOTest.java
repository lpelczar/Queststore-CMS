package dao;

import model.StudentModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DatabaseCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentDAOTest {

    private StudentDAO studentDAO;
    private UserDAO userDAO;

    @BeforeAll
    static void beforeAll() throws IOException {
        String testDbPath = "testDb.db";
        Files.deleteIfExists(new File(testDbPath).toPath());
        OpenCloseConnectionWithDB.setDatabasePath(testDbPath);
        DatabaseCreator.setDatabasePath(testDbPath);
        new DatabaseCreator().createDatabaseFromScript("CreateTables.sql");
    }

    @BeforeEach
    void beforeEach() {
        truncateAllTables();
        studentDAO = new SqliteStudentDAO();
        userDAO = new SqliteUserDAO();
    }

    @Test
    void exportingStudentToDatabaseTest() {
        StudentModel expected = new StudentModel("0", "TestLogin", "TestPassword",
                "TestName","TestLastName");
        userDAO.saveNewUserToDatabase(expected);
        expected.setLogin("New login");
        studentDAO.exportStudent(expected);
        StudentModel result = studentDAO.loadStudent(1);
        assertEquals(expected, result);
    }

    @Test
    void gettingAllStudentsTest() {
        StudentModel studentModel1 = new StudentModel("1", "TestLogin1", "TestPassword1",
                "TestName1","TestLastName1");
        StudentModel studentModel2 = new StudentModel("2", "TestLogin2", "TestPassword2",
                "TestName2","TestLastName2");
        userDAO.saveNewUserToDatabase(studentModel1);
        userDAO.saveNewUserToDatabase(studentModel2);
        ArrayList<StudentModel> expected = new ArrayList<>();
        expected.add(studentModel1);
        expected.add(studentModel2);
        ArrayList<StudentModel> result = studentDAO.getAllStudents();
        assertEquals(expected, result);
    }

    private void truncateAllTables() {
        String truncateLogin = "DELETE FROM logins;";
        String truncateStudents = "DELETE FROM students;";
        String resetIds = "DELETE FROM sqlite_sequence;";
        executeStatement(truncateLogin);
        executeStatement(truncateStudents);
        executeStatement(resetIds);
    }

    private void executeStatement(String sqlStatement) {
        try (PreparedStatement ps = new OpenCloseConnectionWithDB().getConnection().prepareStatement(sqlStatement)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
