package com.example.queststore.dao;

import com.example.queststore.models.Item;

import java.util.List;

public interface ItemDAO {

    List<Item> getItemsBy(int student_id);
    List<Item> getItemsBy(String sqlStatement);
    List<Item> getAllItemsInStore();
    Item getItemBy(int id);
    boolean addItem(Item item);
    boolean updateItem(Item item);

}