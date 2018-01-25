package models;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private Integer price;
    private String description;
    private String category;

    public Item(String name, Integer price, String description, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;

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

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }
}
