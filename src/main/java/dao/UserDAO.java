package dao;

import java.util.ArrayList;


public interface UserDAO {

    public ArrayList<String[]> readDataFromFile();
    public Boolean saveDataToFile(ArrayList<String[]> usersCollection);
    
}