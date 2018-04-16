package dao;

import java.util.ArrayList;


public interface ArtefactDAO {

    public ArrayList<String[]> readDataFromFile();
    public void saveDataToFile(ArrayList<String[]> artefactCollection);
}