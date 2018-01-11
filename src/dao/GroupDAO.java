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

}