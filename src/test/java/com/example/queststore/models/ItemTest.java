package com.example.queststore.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    private Item item;

    @BeforeEach
    void createItemTest() {
        item = new Item(
                1,
                "itemName",
                200,
                "itemDescription",
                "basic"
        );
    }

    @Test
    void getIDTest() {
        assertEquals(1, item.getId());
    }

    @Test
    void setIDTest() {
        item.setId(2);
        assertEquals(2, item.getId());
    }

    @Test
    void getNameTest() {
        assertEquals("itemName", item.getName());
    }

    @Test
    void setNameTest() {
        item.setName("newName");
        assertEquals("newName", item.getName());
    }

    @Test
    void getPrice() {
        Integer priceExpected = 200;
        assertEquals(priceExpected, item.getPrice());
    }

    @Test
    void setPrice() {
        Integer newPrice = 1500;
        item.setPrice(newPrice);
        assertEquals(newPrice, item.getPrice());
    }

    @Test
    void getDescription() {
        assertEquals("itemDescription", item.getDescription());
    }

    @Test
    void setDescription() {
        item.setDescription("newDescription");
        assertEquals("newDescription", item.getDescription());
    }

    @Test
    void getCategory() {
        assertEquals("basic", item.getCategory());
    }

    @Test
    void setCategory() {
        item.setCategory("advanced");
        assertEquals("advanced", item.getCategory());
    }
}