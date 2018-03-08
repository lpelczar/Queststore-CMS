package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.models.Group;
import com.example.queststore.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MentorGroupDAOTest {

    private GroupDAO groupDAO;
    private MentorGroupDAO mentorGroupDAO;
    private UserDAO userDAO;

    @BeforeEach
    void before() throws IOException {
        String testDbPath = "testDb.db";
        Files.deleteIfExists(new File(testDbPath).toPath());
        DbHelper.setDatabasePath(testDbPath);
        new DbHelper().createDatabase();
        this.mentorGroupDAO = new DbMentorGroupDAO();
        this.groupDAO = new DbGroupDAO();
        this.userDAO = new DbUserDAO();
    }

    @Test
    void whenAddThenMentorGroupIsAddedToDb() {
        Group group1 = new Group(1,"Group1");
        Group group2 = new Group(2,"Group2");
        User mentor = new User(1, "Mentor", "Mentor", "mentor@email.com", "mentor",
                "666555666", "Mentor");
        this.userDAO.add(mentor);
        this.groupDAO.add(group1);
        this.groupDAO.add(group2);
        this.mentorGroupDAO.add(group1.getId(), mentor.getId());
        this.mentorGroupDAO.add(group2.getId(), mentor.getId());
        List<String> groupNames = this.groupDAO.getGroupsNamesByMentorId(mentor.getId());
        assertEquals(group1.getGroupName(), groupNames.get(0));
        assertEquals(group2.getGroupName(), groupNames.get(1));
    }

    @Test
    void whenDeleteThenMentorGroupIsDeleted() {
        Group group1 = new Group(1,"Group1");
        User mentor = new User(1, "Mentor", "Mentor", "mentor@email.com", "mentor",
                "666555666", "Mentor");
        this.userDAO.add(mentor);
        this.groupDAO.add(group1);
        this.mentorGroupDAO.add(group1.getId(), mentor.getId());
        this.mentorGroupDAO.delete(group1.getId(), mentor.getId());
        assertTrue(this.groupDAO.getGroupsNamesByMentorId(mentor.getId()).isEmpty());
    }
}
