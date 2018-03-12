package com.example.queststore.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;

    @BeforeEach
    void createInstance() {
        task = new Task(
                1,
                "taskName",
                200,
                "taskDescription",
                "basic"
        );
    }

    @Test
    void getIDTest() {
        assertEquals(1, task.getID());
    }

    @Test
    void setIDTest() {
        task.setID(7);
        assertEquals(7, task.getID());
    }

    @Test
    void getNameTest() {
        assertEquals("taskName", task.getName());
    }

    @Test
    void setNameTest() {
        task.setName("newName");
        assertEquals("newName", task.getName());
    }

    @Test
    void getPointsTest() {
        assertEquals(200, task.getPoints());
    }

    @Test
    void setPointsTest() {
        task.setPoints(500);
        assertEquals(500, task.getPoints());
    }

    @Test
    void getDescriptionTest() {
        assertEquals("taskDescription", task.getDescription());
    }

    @Test
    void setDescriptionTest() {
        task.setDescription("newDescription");
        assertEquals("newDescription", task.getDescription());
    }

    @Test
    void getCategoryTest() {
        assertEquals("basic", task.getCategory());
    }

    @Test
    void setCategoryTest() {
        task.setCategory("advanced");
        assertEquals("advanced", task.getCategory());
    }
}