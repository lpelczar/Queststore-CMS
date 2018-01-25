package dao;

import models.StudentData;
import models.User;

public interface StudentDataDAO {

    StudentData getStudentLevelBy(int student_id);
    boolean add(StudentData student, User user);

}