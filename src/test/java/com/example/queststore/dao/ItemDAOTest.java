package com.example.queststore.dao;

import com.example.queststore.data.DbHelper;
import com.example.queststore.models.Item;
import com.example.queststore.models.StudentData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class ItemDAOTest {
    private static final String DATABASE_PATH = "testDb.db";
    private StudentItemDAO studentItemDAO;
    private ItemDAO itemDAO;
    private StudentData student1;
    private Item item1;


    @BeforeEach
    public void setUpConnectionDB() throws Exception {
        Files.deleteIfExists(new File(DATABASE_PATH).toPath());
        DbHelper.setDatabasePath(DATABASE_PATH);
        new DbHelper().createDatabase();
        studentItemDAO = new DbStudentItemDAO();
        itemDAO = new DbItemDAO();

        item1 = new Item(
                1,
                "itemName1",
                100,
                "itemDescription1",
                "itemCategory"
        );

        student1 = new StudentData(
                1,
                1,
                "teamName1",
                "level1",
                100,
                100
        );
    }


    @Test
    public void addItemTest() {
        itemDAO.addItem(item1);
        Item result = itemDAO.getItemByName(item1.getName());

        assertEquals(item1, result);
    }

    @Test
    public void updateItemTest() {
        itemDAO.addItem(item1);

        item1.setName("changeForTest");
        item1.setCategory("categoryTest");
        item1.setDescription("descriptionTest");
        item1.setPrice(200);

        itemDAO.updateItem(item1);
        Item result = itemDAO.getItemByName(item1.getName());

        assertEquals(item1, result);
    }
}