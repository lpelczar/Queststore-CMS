package models;

public class Item {
    private String name;
    private Integer price;
    private String description;

    public Item(String name, Integer price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void bonus() {
        // implementation what will be change after use this item
    }
}
