package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskDAOTest {

    private TaskDAO taskDAO;

    @BeforeEach
    void before() throws IOException {
        String testDbPath = "testDb.db";
        Files.deleteIfExists(new File(testDbPath).toPath());
        DbHelper.setDatabasePath(testDbPath);
        new DbHelper().createDatabase();
        this.taskDAO = new DbTaskDAO();
    }

    @Test
    void whenAddThenTaskIsAddedToDb() {
        Task expected = new Task(1, "Task", 300, "Simple task", "BASIC");
        this.taskDAO.add(expected);
        Task result = this.taskDAO.getByName(expected.getName());
        assertEquals(expected, result);
    }

    @Test
    void whenUpdateThenTaskIsUpdated() {
        Task task = new Task(1, "Task", 300, "Simple task", "BASIC");
        this.taskDAO.add(task);
        task.setPoints(500);
        task.setDescription("New description");
        task.setName("New name");
        task.setCategory("New category");
        this.taskDAO.update(task);
        assertEquals(task, this.taskDAO.getByName(task.getName()));
    }

    @Test
    void whenGetAllThenReturnAllElements() {
        Task task1 = new Task(1, "Task1", 300, "Simple task", "BASIC");
        Task task2 = new Task(2, "Task2", 350, "Simple2 task", "ADVANCED");
        this.taskDAO.add(task1);
        this.taskDAO.add(task2);
        List<Task> results = this.taskDAO.getAll();
        assertEquals(task1, results.get(0));
        assertEquals(task2, results.get(1));
    }
}
