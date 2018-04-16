package dao;

import java.util.ArrayList;


public interface QuestDAO {

    public ArrayList<String[]> readDataFromFile();
    public Boolean saveDataToFile(ArrayList<String[]> questsCollection);

}
