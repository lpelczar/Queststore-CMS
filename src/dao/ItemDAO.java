package dao;

public interface ItemDAO {

    List<Item> getItemsBy(int student_id);
    List<Item> getItemsBy(String sqlStatement);
}