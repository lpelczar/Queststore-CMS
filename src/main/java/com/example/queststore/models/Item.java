package com.example.queststore.models;


import java.util.Objects;

public class Item {
    private String name;
    private Integer price;
    private String description;
    private String category;
    private int ID;

    public Item(String name, Integer price, String description, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Item(int ID, String name, Integer price, String description, String category) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;

    }

    public int getID() { return ID; }

    public void setID(int ID) { this.ID = ID; }

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

    public String toString() {
        return "ID: " + ID +
                " | Name: " + name +
                " | Price: " + price +
                " | Category: " + category +
                " | Description: " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return ID == item.ID &&
                Objects.equals(name, item.name) &&
                Objects.equals(price, item.price) &&
                Objects.equals(description, item.description) &&
                Objects.equals(category, item.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, price, description, category, ID);
    }
}
