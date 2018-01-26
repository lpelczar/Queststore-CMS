package models;


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
        return "\nName: " + name +
                "\nPrice: " + price +
                "\nCategory: " + category +
                "\nDescription: " + description;
    }
}
