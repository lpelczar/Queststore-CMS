package models;

import java.util.Date;

public class Task {
    private String category;
    private String name;
    private String description;
    private Date deadLine;
    private int points;


    public Task(String name, String category, String description, Date deadLine, int points) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.deadLine = deadLine;
        this.points = points;
    }

    public String toString() {
        return  "Task name: " + name +
                "\nCategory: " + category +
                "\nDeadline: " + deadLine +
                "\n" + description +
                "\nPoints reward: " + points;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String description() {
        return description;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public int getPoints() {
        return points;
    }
}
