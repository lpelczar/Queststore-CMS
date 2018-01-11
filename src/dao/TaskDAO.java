package dao;

import models.Task;

import java.util.*;

public class TaskDAO {

    private List<Task> tasksList = new ArrayList<>();

    public TaskDAO() { readAllTasks();}

    public Task getTaskBy(String name) {

        readAllTasks();
        Task task = null;

        for (Task t : tasksList) {
            if (t.getName().equals(name)) {
                task = t;
            }
        }
        return task;
    }

    public addTask(Task task) {

        readAllTasks();
        if(!tasksList.contains(task)) {
            tasksList.add(task);
            saveAllTasks();
        }
    }

    public boolean removeTask(Task task) {

        readAllTasks();
        if (this.tasksList.contains(task)) {
            this.tasksList.remove(task);
            return true;
        } else {
            return false;
        }
    }
}
