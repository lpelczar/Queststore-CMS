package dao;

import models.Task;

import java.util.*;

public class TaskDAO extends AbstractDAO {

    private List<Task> tasksList = new ArrayList<>();
    private final String FILE_PATH = "src/data/tasks.ser";

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

    @SuppressWarnings("unchecked")
    private void readAllTasks() {

        if (readAllData(FILE_PATH) != null) {
            this.tasksList = (ArrayList<Task>) readAllData(FILE_PATH);
        } else {
            this.tasksList = new ArrayList<>();
        }
    }

    private void saveAllTasks() {

        saveAllData(this.tasksList, FILE_PATH);
    }

}
