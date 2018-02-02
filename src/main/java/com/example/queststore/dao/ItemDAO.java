package main.java.com.example.queststore.dao;

import java.util.List;
import main.java.com.example.queststore.models.Item;

public interface ItemDAO {

    List<Item> getItemsBy(int student_id);
    List<Item> getItemsBy(String sqlStatement);
    List<Item> getAllItemsInStore();
    Item getItemBy(int id);
    boolean addItem(Item item);
    boolean updateItem(Item item);

}