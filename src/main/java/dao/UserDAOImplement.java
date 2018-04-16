package dao;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class UserDAOImplement implements UserDAO {

    private ArrayList<String[]> usersCollection;

    public UserDAOImplement() {
        this.usersCollection = new ArrayList<String[]>();
    }

    public ArrayList<String[]> readDataFromFile() {
        
        ArrayList<String[]> users = this.getUsersCollection();
        File homedir = new File(System.getProperty("user.dir"));
        File fileToRead = new File(homedir, "/bin/resources/users.txt");
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";

        try {
            br = new BufferedReader(new FileReader(fileToRead));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                users.add(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }

    public Boolean saveDataToFile(ArrayList<String[]> usersCollection) {

        int idColumn = 0;
        int nameColumn = 1;
        int surnameColumn = 2;
        int emailColumn = 3;
        int roleColumn = 4;
        int groupColumn = 5;
        BufferedWriter writer = null;

        try {
            File homedir = new File(System.getProperty("user.dir"));
            File fileToWrite = new File(homedir, "bin/resources/users.txt");
            fileToWrite.createNewFile();
            writer = new BufferedWriter(new FileWriter(fileToWrite, false));
            for (int x = 0; x < usersCollection.size(); x++) {
                String[] line = usersCollection.get(x);
                if (line.length == 6) {
                    writer.write(line[idColumn] + "," + line[nameColumn] + ","
                                 + line[surnameColumn] + "," + line[emailColumn] + ","
                                 + line[roleColumn] + "," + line[groupColumn] + "\n");
                } else {
                    writer.write(line[idColumn] + "," + line[nameColumn] + ","
                                 + line[surnameColumn] + "," + line[emailColumn] + ","
                                 + line[roleColumn] + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
        return true;
    }

    public ArrayList<String[]> getUsersCollection() {
      return usersCollection;
    }
}