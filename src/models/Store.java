package models;
import java.util.*;

public class Store {

    private static List<Item> bonuses = new ArrayList<>();


    public List<Item> getBonusesToBuy() {
        return bonuses;
    }

    public void addNewBonus(Item item) {
        bonuses.add(item);
    }

}
