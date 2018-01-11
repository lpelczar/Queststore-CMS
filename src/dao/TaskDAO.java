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
}
