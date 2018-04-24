package queststore.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {
    private Group group;

    @BeforeEach
    public void createInstanceTest() {
        group = new Group(1,"groupName");
    }

    @Test
    public void getGroupNameTest() {
        assertEquals("groupName", group.getGroupName());
    }

    @Test
    public void toStringTest() {
        assertEquals("Group name: groupName", group.toString());
    }

    @Test
    public void getIdTest() {
        assertEquals(1, group.getId());
    }
}