package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.models.Item;
import com.example.queststore.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentItemDAOTest {

    private ItemDAO itemDAO;
    private StudentItemDAO studentItemDAO;
    private UserDAO userDAO;

    @BeforeEach
    public void before() throws IOException {
        String testDbPath = "testDb.db";
        Files.deleteIfExists(new File(testDbPath).toPath());
        DbHelper.setDatabasePath(testDbPath);
        new DbHelper().createDatabase();
        this.studentItemDAO = new DbStudentItemDAO();
        this.itemDAO = new DbItemDAO();
        this.userDAO = new DbUserDAO();
    }

    @Test
    public void whenAddThenStudentItemIsAddedToDb() {
        Item item = new Item(1, "Item", 5, "desc", "basic");
        User student = new User(1, "Student", "Student", "student@email.com", "student",
                "666555666", "Student");
        this.userDAO.add(student);
        this.itemDAO.addItem(item);
        this.studentItemDAO.add(student.getId(), item.getID(), 1);
        List<Item> items = this.itemDAO.getItemsByStudentId(student.getId());
        assertEquals(item, items.get(0));
    }
}
