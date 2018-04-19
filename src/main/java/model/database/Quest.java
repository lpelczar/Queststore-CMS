package model.database;

public class Quest {

    private int questId;
    private String name;
    private String description;
    private int price;

    public Quest(int questId, String name, String description, int price) {
        this.questId = questId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getQuestId() {
        return questId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}
