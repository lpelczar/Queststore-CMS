package model;

public class ArtefactModel {

    private String name;
    private String description;
    private String status;
    private int price;
    private int id;
    // private static int lastID = 0; // powinno wczytywac z pliku!

    public ArtefactModel(String name, String description, String status, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
        //this.id = lastID++;
        this.status = "default";
    }

    public ArtefactModel(String name, String description, String status, int price, int id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int newPrice) {
        this.price = newPrice;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Artefact name: %s.\nDescription: %s.\nPrice: %d.\nStatus: %s",
                name, description, price, status);
    }
}