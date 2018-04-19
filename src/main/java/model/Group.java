package model;

public class Group {

    private int groupId;
    private String groupName;
    private int mentor_id;

    public Group(int groupId, String groupName, int mentor_id) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.mentor_id = mentor_id;
    }

    public String getGroupName() {
        return groupName;
    }
}
