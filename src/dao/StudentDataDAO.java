package dao;

import models.Item;
import models.StudentData;
import models.User;

public interface StudentDataDAO {

    StudentData getStudentDataBy(int student_id);
    boolean add(StudentData student, User user);
    boolean add(int student_id, Item item);
    boolean updateStudentData(StudentData student);
}