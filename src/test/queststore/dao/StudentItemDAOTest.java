package queststore.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queststore.data.DbHelper;
import queststore.models.Item;
import queststore.models.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentItemDAOTest {

    private ItemDAO itemDAO;
    private StudentItemDAO studentItemDAO;
    private UserDAO userDAO;

    @BeforeEach
    void before() throws IOException {
        String testDbPath = "testDb.db";
        Files.deleteIfExists(new File(testDbPath).toPath());
        DbHelper.setDatabasePath(testDbPath);
        new DbHelper().createDatabase();
        this.studentItemDAO = new DbStudentItemDAO();
        this.itemDAO = new DbItemDAO();
        this.userDAO = new DbUserDAO();
    }

    @Test
    void whenAddThenStudentItemIsAddedToDb() {
        Item item = new Item(1, "Item", 5, "desc", "basic");
        User student = new User(1, "Student", "Student", "student@email.com", "student",
                "666555666", "Student");
        this.userDAO.add(student);
        this.itemDAO.addItem(item);
        this.studentItemDAO.add(student.getId(), item.getID(), 1);
        List<Item> items = this.itemDAO.getItemsByStudentId(student.getId());
        List<Integer> itemsIds = this.studentItemDAO.getStudentItemsIdsBy(student.getId());
        assertEquals(item, items.get(0));
        assertEquals((int) itemsIds.get(0), item.getID());
    }

    @Test
    void whenRemoveTeamItemsThenItemsAreRemoved() {
        Item item = new Item(1, "Item", 5, "desc", "Extra");
        User student = new User(1, "Student", "Student", "student@email.com", "student",
                "666555666", "Student");
        this.userDAO.add(student);
        this.itemDAO.addItem(item);
        this.studentItemDAO.add(student.getId(), item.getID(), 1);
        this.studentItemDAO.removeTeamItems();
        List<Integer> itemsIds = this.studentItemDAO.getStudentItemsIdsBy(student.getId());
        assertTrue(itemsIds.isEmpty());
    }
}
