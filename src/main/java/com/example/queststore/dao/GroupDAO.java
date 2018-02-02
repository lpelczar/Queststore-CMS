package com.example.queststore.dao;

import com.example.queststore.models.Entry;
import com.example.queststore.models.Group;

import java.util.List;

public interface GroupDAO {

    List<Entry> getAll();
    Group getByName(String name);
    boolean add(Group group);
    boolean delete(Group group);
}
