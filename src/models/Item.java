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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
