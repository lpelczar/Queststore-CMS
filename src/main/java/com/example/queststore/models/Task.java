package com.example.queststore.models;

import java.util.Objects;

public class Task {

    private int ID;
    private String name;
    private int points;
    private String description;
    private String category;

    public Task(int ID, String name, int points, String description, String category) {
        this.ID = ID;
        this.name = name;
        this.points = points;
        this.description = description;
        this.category = category;
    }

    public Task(String name, int points, String description, String category) {
        this.name = name;
        this.points = points;
        this.description = description;
        this.category = category;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Points: %d, Description: %s, Category: %s",
                name, points, description, category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return ID == task.ID &&
                points == task.points &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(category, task.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID, name, points, description, category);
    }
}
