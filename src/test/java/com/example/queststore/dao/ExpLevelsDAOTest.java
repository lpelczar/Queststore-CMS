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

class ExpLevelsDAOTest {

    private ExpLevelsDAO expLevelsDAO;

    @BeforeEach
    void before() throws IOException {
        String testDbPath = "testDb.db";
        Files.deleteIfExists(new File(testDbPath).toPath());
        DbHelper.setDatabasePath(testDbPath);
        new DbHelper().createDatabase();
        this.expLevelsDAO = new SqliteExpLevelsDAO();
    }

    @Test
    void whenAddThenExpLevelIsAddedToDb() {
        ExpLevel expected = new ExpLevel("TestName", 300);
        this.expLevelsDAO.add(expected);
        ExpLevel result = this.expLevelsDAO.getByName(expected.getName());
        assertEquals(expected, result);
    }

    @Test
    void whenDeleteThenExpLevelDeleted() {
        ExpLevel expLevel = new ExpLevel("TestName", 300);
        this.expLevelsDAO.add(expLevel);
        this.expLevelsDAO.delete(expLevel.getName());
        assertNull(this.expLevelsDAO.getByName(expLevel.getName()));
    }

    @Test
    void whenGetAllThenReturnAllElements() {
        ExpLevel expLevel1 = new ExpLevel("test1", 300);
        ExpLevel expLevel2 = new ExpLevel("test2", 350);
        this.expLevelsDAO.add(expLevel1);
        this.expLevelsDAO.add(expLevel2);
        List<ExpLevel> results = this.expLevelsDAO.getAll();
        assertEquals(expLevel1, results.get(0));
        assertEquals(expLevel2, results.get(1));
    }
}
