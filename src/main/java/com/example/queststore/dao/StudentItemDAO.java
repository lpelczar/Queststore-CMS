package com.example.queststore.dao;

import java.util.List;

public interface StudentItemDAO {

    boolean add(int student_id, int item_id);
    List<Integer> getStudentsItemsBy(int studentID);
}
