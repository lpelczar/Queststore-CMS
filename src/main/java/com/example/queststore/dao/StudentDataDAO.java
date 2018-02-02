package main.java.com.example.queststore.dao;

import main.java.com.example.queststore.models.Item;
import main.java.com.example.queststore.models.StudentData;
import main.java.com.example.queststore.models.User;

public interface StudentDataDAO {

    StudentData getStudentDataBy(int student_id);
    boolean add(StudentData student);
    boolean add(int student_id, Item item);
    boolean updateStudentData(StudentData student);
}