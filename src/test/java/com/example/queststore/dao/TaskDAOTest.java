package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskDAOTest {

    private TaskDAO taskDAO;

    @BeforeEach
    public void before() throws IOException {
        String testDbPath = "testDb.db";
        Files.deleteIfExists(new File(testDbPath).toPath());
        DbHelper.setDatabasePath(testDbPath);
        new DbHelper().createDatabase();
        this.taskDAO = new DbTaskDAO();
    }

    @Test
    public void whenAddThenTaskIsAddedToDb() {
        Task expected = new Task(1, "Task", 300, "Simple task", "BASIC");
        this.taskDAO.add(expected);
        Task result = this.taskDAO.getByName(expected.getName());
        assertEquals(expected, result);
    }
}
