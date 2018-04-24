package queststore.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpLevelTest {
    private ExpLevel expLevel;

    @BeforeEach
    public void constructorTest() {
        expLevel = new ExpLevel("beginner", 200);
    }

    @Test
    public void getNameTest() {
        assertEquals("beginner", expLevel.getName());
    }

    @Test
    public void setNameTest() {
        expLevel.setName("advance");
        assertEquals("advance", expLevel.getName());
    }

    @Test
    public void getValueTest() {
        assertEquals(200, expLevel.getValue());
    }
}