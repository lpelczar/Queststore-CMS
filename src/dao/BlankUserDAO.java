package dao;

import models.*;

import java.util.ArrayList;
import java.util.List;

public class BlankUserDAO extends AbstractDAO {

    private List<BlankUser> blankUserList = new ArrayList<>();
    private final String FILE_PATH = "src/data/blankUsers.ser";

    public BlankUserDAO() { readAllBlankUsers();}

    public void addBlankUser(BlankUser user) {

        readAllBlankUsers();
        if(!blankUserList.contains(user)) {
            blankUserList.add(user);
            saveAllBlankUsers();
        }
    }

    private void saveAllBlankUsers() {

        saveAllData(this.blankUserList, FILE_PATH);
    }

    @SuppressWarnings("unchecked")
    private void readAllBlankUsers() {

        if (readAllData(FILE_PATH) != null) {
            this.blankUserList = (ArrayList<BlankUser>) readAllData(FILE_PATH);
        } else {
            this.blankUserList = new ArrayList<>();
        }
    }

    public List<BlankUser> getBlankUsers() {
        readAllBlankUsers();
        return this.blankUserList;
    }
}
