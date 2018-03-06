package com.example.queststore.models;

public class Group {

    private int id;
    private String groupName;

    public Group(String name) {
        this.groupName = name;
    }

    public Group(int id, String name) {
        this.id = id;
        this.groupName = name;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        return String.format("Group name: %s", groupName);
    }

    public int getId() {
        return id;
    }
}
