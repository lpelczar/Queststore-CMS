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

class LoginDBTest {

    private LoginDB loginDB;
    private GroupDB groupDB;

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
        loginDB = new LoginDBImplement();
        groupDB = new GroupDBImplement();
    }

    @Test
    void insertingAndGettingLoginDataTest() {
        String login = "TestLogin";
        String password = "TestPassword";
        String role = "TestRole";
        String id = "1";
        String[] expected = {id, role};
        loginDB.insertAllLoginData(login, password, role);
        String[] results = loginDB.findUserIdAndRole(login, password);
        assertArrayEquals(expected, results);
    }

    @Test
    void savingStudentUserToDatabaseTest() {
        StudentModel userModel = new StudentModel("1", "TestLogin", "TestPassword",
                "Jerzy", "Mardaus");
        loginDB.saveNewUserToDatabase(userModel);
        String[] expected = {userModel.getId(), "3"};
        String[] results = loginDB.findUserIdAndRole(userModel.getLogin(), userModel.getPassword());
        assertArrayEquals(expected, results);
    }

    @Test
    void savingMentorUserToDatabaseTest() {
        MentorModel userModel = new MentorModel("1", "TestLogin", "TestPassword",
                "Jerzy", "Mardaus");
        loginDB.saveNewUserToDatabase(userModel);
        String[] expected = {userModel.getId(), "2"};
        String[] results = loginDB.findUserIdAndRole(userModel.getLogin(), userModel.getPassword());
        assertArrayEquals(expected, results);
    }

    @Test
    void savingAdminUserToDatabaseTest() {
        AdminModel userModel = new AdminModel("1", "TestLogin", "TestPassword",
                "Jerzy", "Mardaus");
        loginDB.saveNewUserToDatabase(userModel);
        String[] expected = {userModel.getId(), "1"};
        String[] results = loginDB.findUserIdAndRole(userModel.getLogin(), userModel.getPassword());
        assertArrayEquals(expected, results);
    }

    @Test
    void updatingUserLoginAndPasswordTest() {
        String login = "TestLogin";
        String password = "TestPassword";
        String role = "TestRole";
        loginDB.insertAllLoginData(login, password, role);
        String newLogin = "NewLogin";
        String newPassword = "NewPassword";
        loginDB.updateUserLoginAndPassword(newLogin, newPassword, 1);
        String[] results = loginDB.findUserIdAndRole(newLogin, newPassword);
        String[] expected = {"1", role};
        assertArrayEquals(expected, results);
    }

    @Test
    void deletingAllUserLoginDataTest() {
        String login = "TestLogin";
        String password = "TestPassword";
        String role = "TestRole";
        loginDB.insertAllLoginData(login, password, role);
        loginDB.deleteAllUserLoginData(1);
        String[] results = loginDB.findUserIdAndRole(login, password);
        assertNull(results[0]);
        assertNull(results[1]);
    }

    @Test
    void gettingLastIdIfNoEntitiesInDatabaseTest() {
        String id = loginDB.getLastId();
        assertNull(id);
    }

    @Test
    void gettingLastIdTest() {
        loginDB.insertAllLoginData("TestLogin1", "TestPassword1", "TestRole1");
        loginDB.insertAllLoginData("TestLogin2", "TestPassword2", "TestRole2");
        String id = loginDB.getLastId();
        assertEquals("2", id);
    }

    @Test
    void gettingExistingGroupsTest() {
        GroupModel groupModel1 = new GroupModel("Sign1", 1, 1, new ArrayList<>());
        GroupModel groupModel2 = new GroupModel("Sign2", 2, 2, new ArrayList<>());
        groupDB.add(groupModel1);
        groupDB.add(groupModel2);
        Set<String> expected = new HashSet<>(Arrays.asList("Sign1", "Sign2"));
        Set<String> result = loginDB.getExistingGroups();
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
