package model;

import java.util.ArrayList;
import java.util.Objects;

import view.QuestView;

public class QuestModel {

    private static ArrayList<QuestModel> quests = new ArrayList();
    public QuestView view = new QuestView();
    public String name;
    public String description;
    public int price;
    private String quest_id;


    public QuestModel(String quest_id, String name, String description, int price){

        this.quest_id = quest_id;
        this.name = name;
        this.description = description;
        this.price = price;

        quests.add(this);
    }

    public void updateQuestsCollection (QuestModel questToDelete){
        quests.remove(questToDelete);
//        return quests;
    }

    public String getName(){ return this.name; }

    public String getDescription(){ return this.description;}

    public int getPrice(){ return Integer.valueOf(this.price); }

    public static ArrayList<QuestModel> getQuests() {
        return quests;
    }

    public void setName(String newName) { this.name = newName; }

    public void setDescription(String newDescription) { this.description = newDescription;}

    public void setPrice(int newPrice) { this.price = newPrice;}

    public String toString(){
        return String.format("Id.: %s\nName: %s\nDescription: %s\nPrice: %s\n",
                quest_id, name, description, price);
    }

    public int getId() {
        return Integer.valueOf(this.quest_id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestModel that = (QuestModel) o;
        return price == that.price &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(quest_id, that.quest_id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(view, name, description, price, quest_id);
    }
}
