package queststore.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import queststore.data.DbHelper;
import queststore.models.Group;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GroupDAOTest {

    private GroupDAO groupDAO;

    @BeforeEach
    void before() throws IOException {
        String testDbPath = "testDb.db";
        Files.deleteIfExists(new File(testDbPath).toPath());
        DbHelper.setDatabasePath(testDbPath);
        new DbHelper().createDatabase();
        this.groupDAO = new DbGroupDAO();
    }

    @Test
    void whenAddThenGroupIsAddedToDb() {
        Group expected = new Group(1,"Group1");
        this.groupDAO.add(expected);
        Group result = this.groupDAO.getByName(expected.getGroupName());
        assertEquals(expected, result);
    }

    @Test
    void whenDeleteThenGroupDeleted() {
        Group group = new Group(1,"Group1");
        this.groupDAO.add(group);
        this.groupDAO.delete(group);
        assertNull(this.groupDAO.getByName(group.getGroupName()));
    }

    @Test
    void whenGetAllThenReturnAllElements() {
        Group group1 = new Group(1,"Group1");
        Group group2 = new Group(2,"Group2");
        this.groupDAO.add(group1);
        this.groupDAO.add(group2);
        List<Group> results = this.groupDAO.getAll();
        assertEquals(group1, results.get(0));
        assertEquals(group2, results.get(1));
    }
}
