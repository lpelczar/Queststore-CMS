package dao;

import model.Mentor;

public interface MentorDAO {

    void addMentor(Mentor mentor);
    Mentor getMentorBy(int mentorId);
}
