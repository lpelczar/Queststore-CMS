package models;
import java.util.*;

public class Group {
    private List<Team> group;
    private final int ID;
    private static int nextID = 1;
    private String groupName;


    public Group(String name) {
        this.groupName = name;
        this.ID = nextID;
        nextID++;
    }

    public boolean addTeam(Team team) {
        if (!group.contains(team)) {
            group.add(team);
            return true;
        }
        return false;
    }

    public boolean removeTeam(Team team) {
        if (group.contains(team)) {
            gorup.remove(team);
            return true;
        }
        return false;
    }

    public int getID() {
        return ID;
    }

    public List<Team> getGroup() {
        return group;
    }
}
