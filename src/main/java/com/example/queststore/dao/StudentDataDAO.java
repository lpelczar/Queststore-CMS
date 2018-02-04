package com.example.queststore.dao;

import com.example.queststore.models.StudentData;

public interface StudentDataDAO {

    StudentData getStudentDataBy(int student_id);
    boolean add(StudentData student);
    boolean updateStudentData(StudentData student);
}