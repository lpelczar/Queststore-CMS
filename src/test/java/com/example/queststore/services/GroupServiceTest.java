package com.example.queststore.services;

import com.example.queststore.dao.*;
import com.example.queststore.data.DbHelper;
import com.example.queststore.models.Group;
import com.example.queststore.models.User;
import com.example.queststore.views.GroupView;
import com.example.queststore.views.MentorView;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class GroupServiceTest {

    @Mock
    private GroupView mockGroupView;

    @Mock
    private MentorView mockMentorView;


    @Mock
    private UserDAO mockUserDAO;

    @Mock
    private MentorGroupDAO mockMentorGroupDAO;

    @Mock
    private StudentDataDAO mockStudentDataDAO;

    private DbGroupDAO testDao;
    private GroupService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void dbSetUp() throws IOException {
        DbHelper.setDatabasePath("test.db");

        Files.deleteIfExists(new File(DbHelper.getDatabasePath()).toPath());
        DbHelper dbHelper = new DbHelper();
        dbHelper.createDatabase();
    }

    @AfterEach
    void tearDown() throws IOException {
//        Files.deleteIfExists(new File(DbHelper.getDatabasePath()).toPath());
    }

    @Test
    void testIfCreateGroupCompletes() {
        testDao = new DbGroupDAO();
        service = new GroupService(mockGroupView, mockMentorView, testDao,
                mockUserDAO, mockMentorGroupDAO, mockStudentDataDAO);

        boolean actual = service.createGroup("test name");
        Assertions.assertTrue(actual);
    }

    @Test
    void testIfCreatedGroupExists() {
        testDao = new DbGroupDAO();
        service = new GroupService(mockGroupView, mockMentorView, testDao,
                mockUserDAO, mockMentorGroupDAO, mockStudentDataDAO);

        service.createGroup("test 1");
        service.createGroup("test 2");

        Assertions.assertEquals("test 2", testDao.getByName("test 2").getGroupName());
    }

    @Test
    void testIfMentorExists() {
        testDao = new DbGroupDAO();
        DbUserDAO userDAO = new DbUserDAO();

        service = new GroupService(mockGroupView, mockMentorView, testDao,
                userDAO, mockMentorGroupDAO, mockStudentDataDAO);
        userDAO.add(new User(1, "1", "login", "1", "1", "1", "Mentor"));
        Assertions.assertTrue(service.verifyMentorExists("login"));
    }
}