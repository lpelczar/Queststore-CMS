package dao;

import model.AdminModel;
import model.MentorModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DatabaseCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class AdminDBImplementTest {

    private AdminDB adminDao;
    private LoginDB userDao;
    private String testDbPath = "testDb.db";
    private String createTables = "CreateTables.sql";
    private AdminModel admin;
    private MentorModel mentor;

    @BeforeAll
    static void changeDbPathForTests() {
        String testDbPath = "testDb.db";
        OpenCloseConnectionWithDB.setDatabasePath(testDbPath);
    }

    @BeforeEach
    void createAdminDao() throws IOException {
        adminDao = new AdminDBImplement();
        userDao = new LoginDBImplement();
        admin = new AdminModel("1", "login", "password", "name", "lastName", "email");
        mentor = new MentorModel("2", "loginMentor", "passwordMentor", "nameMentor", "lastNameMentor", "emailMentor");

        Files.deleteIfExists(new File(testDbPath).toPath());
        DatabaseCreator.setDatabasePath(testDbPath);
        new DatabaseCreator().createDatabaseFromScript(createTables);

        userDao.insertAllLoginData("login", "password", "1");
        adminDao.insertAdminData(admin.getName(), admin.getLastName(), admin.getEmail());

        userDao.saveNewUserToDatabase(mentor);
    }

    @Test
    void insertAdminTest() {
        AdminModel result = adminDao.loadAdmin(1);
        assertEquals(admin, result);
    }

    @Test
    void updateUserLoginTest() {
        adminDao.updateUserLogin("login", "1");
        AdminModel result = adminDao.loadAdmin(1);

        assertEquals("login", result.getLogin());
    }
}