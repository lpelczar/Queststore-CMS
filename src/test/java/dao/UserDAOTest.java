package dao;

import model.AdminModel;
import model.GroupModel;
import model.MentorModel;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserDAOTest {

    private UserDAO userDAO;
    private GroupDAO groupDAO;

    @BeforeAll
    static void beforeAll() throws IOException {
        String testDbPath = "testDb.db";
        Files.deleteIfExists(new File(testDbPath).toPath());
        OpenCloseConnectionWithDB.setDatabasePath(testDbPath);
        DatabaseCreator.setDatabasePath(testDbPath);
        new DatabaseCreator().createDatabaseFromScript("createTables.sql");
    }

    @BeforeEach
    void beforeEach() {
        truncateAllTables();
        userDAO = new SqliteUserDAO();
        groupDAO = new SqliteGroupDAO();
    }

    @Test
    void insertingAndGettingLoginDataTest() {
        String login = "TestLogin";
        String password = "TestPassword";
        String role = "TestRole";
        String id = "1";
        String[] expected = {id, role};
        userDAO.insertAllLoginData(login, password, role);
        String[] results = userDAO.findUserIdAndRole(login, password);
        assertArrayEquals(expected, results);
    }

    @Test
    void savingStudentUserToDatabaseTest() {
        StudentModel userModel = new StudentModel("1", "TestLogin", "TestPassword",
                "Jerzy", "Mardaus");
        userDAO.saveNewUserToDatabase(userModel);
        String[] expected = {userModel.getId(), "3"};
        String[] results = userDAO.findUserIdAndRole(userModel.getLogin(), userModel.getPassword());
        assertArrayEquals(expected, results);
    }

    @Test
    void savingMentorUserToDatabaseTest() {
        MentorModel userModel = new MentorModel("1", "TestLogin", "TestPassword",
                "Jerzy", "Mardaus");
        userDAO.saveNewUserToDatabase(userModel);
        String[] expected = {userModel.getId(), "2"};
        String[] results = userDAO.findUserIdAndRole(userModel.getLogin(), userModel.getPassword());
        assertArrayEquals(expected, results);
    }

    @Test
    void savingAdminUserToDatabaseTest() {
        AdminModel userModel = new AdminModel("1", "TestLogin", "TestPassword",
                "Jerzy", "Mardaus");
        userDAO.saveNewUserToDatabase(userModel);
        String[] expected = {userModel.getId(), "1"};
        String[] results = userDAO.findUserIdAndRole(userModel.getLogin(), userModel.getPassword());
        assertArrayEquals(expected, results);
    }

    @Test
    void updatingUserLoginAndPasswordTest() {
        String login = "TestLogin";
        String password = "TestPassword";
        String role = "TestRole";
        userDAO.insertAllLoginData(login, password, role);
        String newLogin = "NewLogin";
        String newPassword = "NewPassword";
        userDAO.updateUserLoginAndPassword(newLogin, newPassword, 1);
        String[] results = userDAO.findUserIdAndRole(newLogin, newPassword);
        String[] expected = {"1", role};
        assertArrayEquals(expected, results);
    }

    @Test
    void deletingAllUserLoginDataTest() {
        String login = "TestLogin";
        String password = "TestPassword";
        String role = "TestRole";
        userDAO.insertAllLoginData(login, password, role);
        userDAO.deleteAllUserLoginData(1);
        String[] results = userDAO.findUserIdAndRole(login, password);
        assertNull(results[0]);
        assertNull(results[1]);
    }

    @Test
    void gettingLastIdIfNoEntitiesInDatabaseTest() {
        String id = userDAO.getLastId();
        assertNull(id);
    }

    @Test
    void gettingLastIdTest() {
        userDAO.insertAllLoginData("TestLogin1", "TestPassword1", "TestRole1");
        userDAO.insertAllLoginData("TestLogin2", "TestPassword2", "TestRole2");
        String id = userDAO.getLastId();
        assertEquals("2", id);
    }

    @Test
    void gettingExistingGroupsTest() {
        GroupModel groupModel1 = new GroupModel("Sign1", 1, 1, new ArrayList<>());
        GroupModel groupModel2 = new GroupModel("Sign2", 2, 2, new ArrayList<>());
        groupDAO.add(groupModel1);
        groupDAO.add(groupModel2);
        Set<String> expected = new HashSet<>(Arrays.asList("Sign1", "Sign2"));
        Set<String> result = userDAO.getExistingGroups();
        assertEquals(expected, result);
    }

    private void truncateAllTables() {
        String truncateGroupNames = "DELETE FROM group_names;";
        String resetRowIdGroupNames = "DELETE FROM sqlite_sequence WHERE name= 'group_names';";
        String truncateLogins = "DELETE FROM logins;";
        String resetRowIdLogins = "DELETE FROM sqlite_sequence WHERE name= 'logins';";
        executeStatement(truncateGroupNames);
        executeStatement(resetRowIdGroupNames);
        executeStatement(truncateLogins);
        executeStatement(resetRowIdLogins);
    }

    private void executeStatement(String sqlStatement) {
        try (PreparedStatement ps = new OpenCloseConnectionWithDB().getConnection().prepareStatement(sqlStatement)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
