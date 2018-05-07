package com.example.queststore.dao;

import com.example.queststore.models.Item;

import java.util.List;

public interface ItemDAO {

    List<Item> getItemsByStudentId(int student_id);
    List<Item> getItemsByCategory(String category);
    List<Item> getAllItems();
    Item getItemByName(String itemName);
    Item getItemById(int id);
    boolean add(Item item);
    boolean update(Item item);
    void setDatabasePath(String path);
}