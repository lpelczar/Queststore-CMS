package dao;

import models.Mentor;

import java.util.*;

public class MentorDAO extends AbstractDAO {

    private List<Mentor> mentorsList = new ArrayList<>();
    private final String FILE_PATH = "src/data/mentors.ser";

    public MentorDAO() { readAllMentors();}

    public void addMentor(Mentor mentor) {

        readAllMentors();
        if(!mentorsList.contains(mentor)) {
            mentorsList.add(mentor);
            saveAllMentors();
        }
    }

    private void saveAllMentors() {

        saveAllData(this.mentorsList, FILE_PATH);
    }
}