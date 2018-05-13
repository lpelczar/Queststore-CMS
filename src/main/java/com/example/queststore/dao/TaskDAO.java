package com.example.queststore.dao;

import com.example.queststore.models.Task;

import java.util.List;

public interface TaskDAO {

    List<Task> getAll();
    List<Task> getTasksByStudentId(int id);
    Task getByName(String name);
    Task getById(int id);
    boolean add(Task task);
    boolean update(Task task);
    boolean delete(Task task);
    void setDatabasePath(String path);
}
