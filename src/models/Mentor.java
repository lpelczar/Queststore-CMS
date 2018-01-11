package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mentor extends User implements Serializable {

    private List<Integer> groups = new ArrayList<>();

    public Mentor(String name, String login, String password, String email, String phoneNumber) {
        super(name, login, password, email, phoneNumber);
    }

    public void addGroup(int groupID) {
        if (!groups.contains(groupID)) {
            groups.add(groupID);
        }
    }
}
