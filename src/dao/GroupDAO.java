package dao;

import models.Group;

import java.util.*;

public class GroupDAO extends AbstractDAO {

    private List<Group> groupsList = new ArrayList<>();
    private final String FILE_PATH = "src/data/groups.ser";

    public GroupDAO() { readAllGroups();}

    public Group getGroupBy(int id) {

        readAllGroups();
        Group group = null;

        for (Group g : groupsList) {
            if (g.getID() == id) {
                group = g;
            }
        }
        return group;
    }

    public void addGroup(Group group) {

        readAllGroups();
        if(!groupsList.contains(group)) {
            groupsList.add(group);
            saveAllGroups();
        }
    }

    public boolean removeGroup(Group group) {

        readAllGroups();
        if (this.groupsList.contains(group)) {
            this.groupsList.remove(group);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private void readAllGroups() {

        if (readAllData(FILE_PATH) != null) {
            this.groupsList = (ArrayList<Group>) readAllData(FILE_PATH);
        } else {
            this.groupsList = new ArrayList<>();
        }
    }

    private void saveAllGroups() {

        saveAllData(this.groupsList, FILE_PATH);
    }
}