package com.example.queststore.models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id &&
                Objects.equals(groupName, group.groupName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, groupName);
    }
}
