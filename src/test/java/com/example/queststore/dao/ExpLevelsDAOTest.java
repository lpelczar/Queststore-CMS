package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.models.ExpLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

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

    @Test
    public void whenGetAllThenReturnAllElements() {
        ExpLevel expLevel1 = new ExpLevel("test1", 300);
        ExpLevel expLevel2 = new ExpLevel("test2", 350);
        this.expLevelsDAO.add(expLevel1);
        this.expLevelsDAO.add(expLevel2);
        List<ExpLevel> results = this.expLevelsDAO.getAll();
        assertEquals(expLevel1, results.get(0));
        assertEquals(expLevel2, results.get(1));
    }
}
