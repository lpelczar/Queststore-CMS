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

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentDataDAOTest {

    private StudentDataDAO studentDataDAO;
    private UserDAO userDAO;
    private GroupDAO groupDAO;

    @BeforeEach
    void before() throws IOException {
        String testDbPath = "testDb.db";
        Files.deleteIfExists(new File(testDbPath).toPath());
        DbHelper.setDatabasePath(testDbPath);
        new DbHelper().createDatabase();
        this.studentDataDAO = new DbStudentDataDAO();
        this.userDAO = new DbUserDAO();
        this.groupDAO = new DbGroupDAO();
    }

    @Test
    void whenAddThenStudentDataIsAddedToDb() {
        User student = new User(1, "Student", "Student", "student@email.com", "student",
                "666555666", "Student");
        Group group1 = new Group(1,"Group1");
        StudentData expected = new StudentData(student.getId(), group1.getId(), "team", "pro",
                13, 30);
        this.userDAO.add(student);
        this.groupDAO.add(group1);
        this.studentDataDAO.add(expected);
        StudentData result = this.studentDataDAO.getStudentDataBy(student.getId());
        assertEquals(expected, result);
    }
}
