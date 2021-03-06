package com.example.queststore.dao;

import com.example.queststore.dao.sqlite.SqliteItemDAO;
import com.example.queststore.dao.sqlite.SqliteStudentItemDAO;
import com.example.queststore.dao.sqlite.SqliteUserDAO;
import com.example.queststore.data.DbHelper;
import com.example.queststore.models.Item;
import com.example.queststore.models.StudentData;
import com.example.queststore.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {
    private static final String DATABASE_PATH = "testDb.db";
    private StudentItemDAO studentItemDAO;
    private ItemDAO itemDAO;
    private StudentData student1;
    private UserDAO userDAO;

    private Item item1;
    private Item item2;


    @BeforeEach
    void setUpConnectionDB() throws Exception {
        Files.deleteIfExists(new File(DATABASE_PATH).toPath());
        DbHelper dbHelper = new DbHelper();
        dbHelper.setDatabasePath(DATABASE_PATH);
        dbHelper.createDatabase();
        studentItemDAO = new SqliteStudentItemDAO();
        studentItemDAO.setDatabasePath(DATABASE_PATH);
        userDAO = new SqliteUserDAO();
        userDAO.setDatabasePath(DATABASE_PATH);
        itemDAO = new SqliteItemDAO();
        itemDAO.setDatabasePath(DATABASE_PATH);

        item1 = new Item(
                1,
                "itemName1",
                100,
                "itemDescription1",
                "itemCategoryMagic"
        );

        item2 = new Item(
                2,
                "itemName2",
                150,
                "itemDescription2",
                "itemCategoryBasic"
        );

        itemDAO.add(item1);
        itemDAO.add(item2);
    }
    
    @Test
    public void addItemTest() {
        Item result = itemDAO.getItemByName(item1.getName());

        assertEquals(item1, result);
    }

    @Test

    public void updateItemTest() {
        item1.setName("changeForTest");
        item1.setCategory("categoryTest");
        item1.setDescription("descriptionTest");
        item1.setPrice(200);

        itemDAO.update(item1);
        Item result = itemDAO.getItemByName(item1.getName());

        assertEquals(item1, result);
    }

    @Test
    public void getAllItemsTest() {
        List<Item> result = itemDAO.getAllItems();
        assertEquals(item1, result.get(0));
        assertEquals(item2, result.get(1));
    }

    @Test
    public void getItemsByCategoryTest() {
        List<Item> result = itemDAO.getItemsByCategory("itemCategoryBasic");
        assertEquals(item2, result.get(0));
    }

    @Test
    public void getItemByIdTest() {
        Item result = itemDAO.getItemById(1);
        assertEquals(item1, result);
    }

    @Test
    public void getItemByNameTest() {
        Item result = itemDAO.getItemByName("itemName1");
        assertEquals(item1, result);
    }

    @Test
    public void getItemsByStudentIdTest() {
        userDAO.add(new User(
                1,
                "userName1",
                "userLogin1",
                "userEmail1",
                "userPassword1",
                "userPhoneNumber1",
                "student"
        ));

        student1 = new StudentData(
                1,
                1,
                "teamName1",
                "level1",
                100,
                100
        );
        studentItemDAO.add(student1.getId(), item1.getId(), 0);
        List<Item> result = itemDAO.getItemsByStudentId(student1.getId());

        assertEquals(item1, result.get(0));
    }
}