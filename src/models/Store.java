package models;
import java.io.Serializable;
import java.util.*;

public class Store implements Serializable {

    private static List<Item> bonuses = new ArrayList<>();


    public List<Item> getBonusesToBuy() {
        return bonuses;
    }

    public void addNewBonus(Item item) {
        bonuses.add(item);
    }

}
