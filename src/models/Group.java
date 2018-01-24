package models;

public class Group implements Entry {

    private String groupName;

    public Group(String name) {
        this.groupName = name;
    }

    public String getGroupName() {
        return groupName;
    }
}
