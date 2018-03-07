package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.models.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GroupDAOTest {

    private GroupDAO groupDAO;

    @BeforeEach
    public void before() throws IOException {
        String testDbPath = "testDb.db";
        Files.deleteIfExists(new File(testDbPath).toPath());
        DbHelper.setDatabasePath(testDbPath);
        new DbHelper().createDatabase();
        this.groupDAO = new DbGroupDAO();
    }

    @Test
    public void whenAddThenGroupIsAddedToDb() {
        Group expected = new Group(1,"Group1");
        this.groupDAO.add(expected);
        Group result = this.groupDAO.getByName(expected.getGroupName());
        assertEquals(expected, result);
    }

    @Test
    public void whenDeleteThenGroupDeleted() {
        Group group = new Group(1,"Group1");
        this.groupDAO.add(group);
        this.groupDAO.delete(group);
        assertNull(this.groupDAO.getByName(group.getGroupName()));
    }
}
