package com.example.queststore.dao;

import com.example.queststore.models.Item;

import java.util.List;

public interface ItemDAO {

    List<Item> getItemsByStudentId(int student_id);
    List<Item> getAllItems();
    Item getItemById(int id);
    boolean addItem(Item item);
    boolean updateItem(Item item);

}