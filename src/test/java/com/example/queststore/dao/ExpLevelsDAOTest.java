package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.models.ExpLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ExpLevelsDAOTest {

    private ExpLevelsDAO expLevelsDAO;

    @BeforeEach
    public void before() throws IOException {
        String testDbPath = "testDb.db";
        Files.deleteIfExists(new File(testDbPath).toPath());
        DbHelper.setDatabasePath(testDbPath);
        new DbHelper().createDatabase();
        this.expLevelsDAO = new DbExpLevelsDAO();
    }

    @Test
    public void whenAddThenExpLevelIsAddedToDb() {
        String expLevelName = "TestName";
        ExpLevel expLevel = new ExpLevel(expLevelName, 300);
        this.expLevelsDAO.add(expLevel);
        ExpLevel result = this.expLevelsDAO.getByName(expLevelName);
        assertEquals(expLevel.getName(), result.getName());
        assertEquals(expLevel.getValue(), result.getValue());
    }

    @Test
    public void whenDeleteThenExpLevelDeleted() {
        String expLevelName = "TestName";
        ExpLevel expLevel = new ExpLevel(expLevelName, 300);
        this.expLevelsDAO.add(expLevel);
        this.expLevelsDAO.delete(expLevel.getName());
        assertNull(this.expLevelsDAO.getByName(expLevel.getName()));
    }

}
