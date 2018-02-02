package com.example.queststore.dao;

import com.example.queststore.models.Item;
import com.example.queststore.models.StudentData;

public interface StudentDataDAO {

    StudentData getStudentDataBy(int student_id);
    boolean add(StudentData student);
    boolean add(int student_id, Item item);
    boolean updateStudentData(StudentData student);
}