package com.example.queststore.dao;

import java.util.List;

public interface StudentItemDAO {

    List<Integer> getStudentsItemsBy(int studentID);
    boolean add(int studentId, int itemId, int isUsed);
    boolean markItemAsUsed(int studentId, int itemId);
}
