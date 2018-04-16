package dao;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class QuestDAOImplement implements QuestDAO {

    private ArrayList<String[]> questsCollection;

    public QuestDAOImplement() {
        this.questsCollection = new ArrayList<String[]>();
    }

    public ArrayList<String[]> readDataFromFile() {
        
        ArrayList<String[]> quests = this.getQuestsCollection();
        File homedir = new File(System.getProperty("user.dir"));
        File fileToRead = new File(homedir, "/bin/resources/quests.txt");
        BufferedReader br = null;
        String line = "";
        String splitBy = ",";

        try {
            br = new BufferedReader(new FileReader(fileToRead));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                quests.add(data);
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
        return quests;
    }

    public Boolean saveDataToFile(ArrayList<String[]> questsCollection) {

        int idColumn = 0;
        int descriptionColumn = 1;
        int rewardColumn = 2;
        BufferedWriter writer = null;

        try {
            File homedir = new File(System.getProperty("user.dir"));
            File fileToWrite = new File(homedir, "bin/resources/quests.txt");
            fileToWrite.createNewFile();
            writer = new BufferedWriter(new FileWriter(fileToWrite, false));
            for (int x = 0; x < questsCollection.size(); x++) {
                String[] line = questsCollection.get(x);
                writer.write(line[idColumn] + "," + line[descriptionColumn] + ","
                             + line[rewardColumn] + "\n");
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

    public String getLastId(){
        String lastId = null;
        ArrayList<String[]> allLogins = readDataFromFile();
        for (String[] usersInfo : allLogins){
            int highestId = Integer.parseInt(usersInfo[0]);
            lastId = Integer.valueOf(highestId).toString();
        }
        return lastId;
    }

    public ArrayList<String[]> getQuestsCollection() {
      return questsCollection;
    }


}
