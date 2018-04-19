package dao;

import model.Mentor;

public interface MentorDAO {

    void addMentor(Mentor mentor);
    void updateMentor(Mentor mentor, int id);
    Mentor getMentorBy(int mentorId);

}
