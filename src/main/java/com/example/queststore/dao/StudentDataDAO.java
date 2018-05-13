package com.example.queststore.dao;

import com.example.queststore.models.StudentData;

import java.util.List;

public interface StudentDataDAO {

    StudentData getStudentDataByStudentId(int student_id);
    List<StudentData> getAllStudentsData();
    List<StudentData> getStudentsDataByTeamName(String teamName);
    boolean add(StudentData student);
    boolean updateStudentData(StudentData student);
    void setDatabasePath(String path);
}