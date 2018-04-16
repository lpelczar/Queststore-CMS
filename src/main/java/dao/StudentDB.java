package dao;

import model.GroupModel;
import model.StudentModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface StudentDB {

    StudentModel loadStudent(int id);
    void exportStudent(StudentModel student);
    ArrayList<StudentModel> getAllStudents();
    GroupModel getMentorGroupByMentorID(String mentorId);
    void insertNewStudentToGroup(int studentId, int groupId);
}
