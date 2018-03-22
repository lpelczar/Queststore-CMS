package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.models.Group;
import com.example.queststore.models.StudentData;
import com.example.queststore.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DbStudentDataDAOTest {

    @BeforeEach
    public void dbSetup() throws IOException {

        DbHelper.setDatabasePath("test.db");

        Files.deleteIfExists(new File(DbHelper.getDatabasePath()).toPath());

        DbHelper dbHelper = new DbHelper();
        dbHelper.createDatabase();

    }

    @Test
    public void studentDataAdditionAndRetrievalAreSymmetric() {

        GroupDAO groupDao = new DbGroupDAO();
        UserDAO userDao = new DbUserDAO();
        StudentDataDAO studentDataDao = new DbStudentDataDAO();

        int userId = 1;
        Group group = new Group(1, "test");
        groupDao.add(group);

        User user = new User(userId, "name", "login", "email", "password", "01234", "role");
        userDao.add(user);

        StudentData expected = new StudentData();
        expected.setStudentId(userId);
        studentDataDao.add(expected);

        StudentData retrieved = studentDataDao.getStudentDataByStudentId(expected.getId());

        assertEquals(expected, retrieved);

    }

    @Test
    public void duplicateStudentIdDataInsertionShouldFail() {

        GroupDAO groupDao = new DbGroupDAO();
        UserDAO userDao = new DbUserDAO();
        StudentDataDAO studentDataDao = new DbStudentDataDAO();

        Group group = new Group(1, "test");
        groupDao.add(group);

        User user = new User(1, "name", "login", "email", "password", "01234", "role");
        userDao.add(user);

        StudentData instanceOne = new StudentData();
        instanceOne.setStudentId(1);
        StudentData instanceTwo = new StudentData();
        instanceTwo.setStudentId(1);

        List<StudentData> expected = new ArrayList<>();
        expected.add(instanceOne);
        expected.add(instanceTwo);

        studentDataDao.add(instanceOne);
        studentDataDao.add(instanceTwo);

        List<StudentData> current = studentDataDao.getAllStudentsData();

        assertFalse(expected.equals(current));

    }

}