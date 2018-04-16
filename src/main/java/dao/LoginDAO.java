package dao;

import java.util.ArrayList;


public interface LoginDAO {

    public ArrayList<String[]> readDataFromFile();
    public Boolean saveDataToFile(ArrayList<String[]> loginCollection);
    public String[] getLoginAndPassword(String id);

}