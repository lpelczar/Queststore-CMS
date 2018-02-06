package com.example.queststore.dao;

import com.example.queststore.models.Task;

public interface TaskDAO {

    Task getByName(String name);
    boolean add(Task task);
}
