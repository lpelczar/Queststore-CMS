package dao;

import java.util.List;
import models.Item;

public interface ItemDAO {

    List<Item> getItemsBy(int student_id);
    List<Item> getItemsBy(String sqlStatement);
}