package queststore.dao;


import queststore.models.StudentData;

import java.util.List;

public interface StudentDataDAO {

    StudentData getStudentDataBy(int student_id);
    List<StudentData> getAllStudents();
    List<StudentData> getStudentsInSameTeamBy(String teamName);
    boolean add(StudentData student);
    boolean updateStudentData(StudentData student);
}