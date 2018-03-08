package com.example.queststore.dao;


import com.example.queststore.data.DbHelper;
import com.example.queststore.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDAOTest {
    private UserDAO userDAO;
    private User user1;
    private User user2;
    private static final String DATABASE_PATH = "testDb.db";

    @BeforeEach
    public void setUpConnectionDB() throws Exception {
        Files.deleteIfExists(new File(DATABASE_PATH).toPath());
        DbHelper.setDatabasePath(DATABASE_PATH);
        new DbHelper().createDatabase();
        userDAO = new DbUserDAO();

        user1 = new User(
                1,
                "userName1",
                "userLogin1",
                "userEmail1",
                "userPassword1",
                "userPhoneNumber1",
                "userRole"
        );

        user2 = new User(
                2,
                "userName2",
                "userLogin2",
                "userEmail2",
                "userPassword2",
                "userPhoneNumber2",
                "userRole"
        );
    }

    @Test
    public void addUserToDbTest() {
        userDAO.add(user1);
        User result = userDAO.getByLogin(user1.getLogin());

        assertEquals(user1, result);

    }

    @Test
    public void updateUserInDbTest() {
        userDAO.add(user1);
        User userTest = userDAO.getByLogin(user1.getLogin());

        userTest.setName("ChangeForTest");
        userDAO.update(userTest);

        User result = userDAO.getByLogin(userTest.getLogin());

        assertEquals(userTest, result);
    }

    @Test
    public void deleteUserFromDbTest() {
        userDAO.add(user1);
        User userFromDB = userDAO.getByLogin(user1.getLogin());
        userDAO.delete(userFromDB);
        User result = userDAO.getByLogin(user1.getLogin());

        assertEquals(null, result);
    }

    @Test
    public void getByIdTest() {
        userDAO.add(user1);
        User result = userDAO.getById(1);

        assertEquals("userName1", result.getName());
    }

    @Test
    public void getByLoginAndPasswordTest() {
        userDAO.add(user1);
        User result = userDAO.getByLoginAndPassword(user1.getLogin(), user1.getPassword());

        assertEquals("userLogin1", result.getLogin());
        assertEquals("userPassword1", result.getPassword());
    }

    @Test
    public void getByLoginAndRoleTest() {
        userDAO.add(user1);
        User result = userDAO.getByLoginAndRole(user1.getLogin(), user1.getRole());

        assertEquals("userLogin1", result.getLogin());
        assertEquals("userRole", result.getRole());
    }

    @Test
    public void getByLoginTest() {
        userDAO.add(user1);
        User result = userDAO.getByLogin(user1.getLogin());

        assertEquals("userLogin1", result.getLogin());
        assertEquals("userName1", result.getName());
    }

    @Test
    public void getByEmailTest() {
        userDAO.add(user1);
        User result = userDAO.getByEmail(user1.getEmail());

        assertEquals("userEmail1", result.getEmail());
        assertEquals("userName1", result.getName());
    }

    @Test
    public void getByPhoneNumberTest() {
        userDAO.add(user1);
        User result = userDAO.getByPhoneNumber(user1.getPhoneNumber());

        assertEquals("userPhoneNumber1", result.getPhoneNumber());
        assertEquals("userLogin1", result.getLogin());
    }

    @Test
    public void getAllByRoleTest() {
        userDAO.add(user1);
        userDAO.add(user2);
        List<User> result = userDAO.getAllByRole("userRole");

        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));
    }

    @Test
    public void getAllTest() {
        userDAO.add(user1);
        userDAO.add(user2);

        List<User> result = userDAO.getAll();

        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));
    }
}