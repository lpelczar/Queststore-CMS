package com.example.queststore.models;

import java.util.Objects;

public class Task {

    private int id;
    private String name;
    private int points;
    private String description;
    private String category;

    public Task(String name, int points, String description, String category) {
        this.name = name;
        this.points = points;
        this.description = description;
        this.category = category;
    }

    public Task(int id, String name, int points, String description, String category) {
        this(name, points, description, category);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id == task.id &&
                points == task.points &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                Objects.equals(category, task.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, points, description, category);
    }
}
