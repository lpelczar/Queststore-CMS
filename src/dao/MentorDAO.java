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

    @SuppressWarnings("unchecked")
    private void readAllMentors() {

        if (readAllData(FILE_PATH) != null) {
            this.mentorsList = (ArrayList<Mentor>) readAllData(FILE_PATH);
        } else {
            this.mentorsList = new ArrayList<>();
        }
    }

    public List<Mentor> getMentors() {
        readAllMentors();
        return this.mentorsList;
    }
}