package queststore.dao;


import queststore.models.Item;

import java.util.List;

public interface ItemDAO {

    List<Item> getItemsByStudentId(int student_id);
    List<Item> getItemsByCategory(String category);
    List<Item> getAllItems();
    Item getItemByName(String itemName);
    Item getItemById(int id);
    boolean addItem(Item item);
    boolean updateItem(Item item);
}