package dao;

import models.Student;

import java.util.*;

public class StudentDAO extends AbstractDAO {

    private List<Student> studentsList = new ArrayList<>();
    private final String FILE_PATH = "src/data/students.ser";

    public StudentDAO() { readAllStudents();}

    public void addStudent(Student student) {

        readAllStudents();
        if(!studentsList.contains(student)) {
            studentsList.add(student);
            saveAllStudents();
        }
    }

    private void saveAllStudents() {

        saveAllData(this.studentsList, FILE_PATH);
    }

    @SuppressWarnings("unchecked")
    private void readAllStudents() {

        if (readAllData(FILE_PATH) != null) {
            this.studentsList = (ArrayList<Student>) readAllData(FILE_PATH);
        } else {
            this.studentsList = new ArrayList<>();
        }
    }

    public List<Student> getStudents() {
        readAllStudents();
        return this.studentsList;
    }

}