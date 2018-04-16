package model;

import java.util.ArrayList;
import java.util.Objects;


public class AdminModel extends UserModel {

    private ArrayList<MentorModel> mentors = new ArrayList<>();

    public AdminModel(String id, String login, String password, String name, String lastName){
        super(id, login, password, name, lastName);
    }

    public AdminModel(String id, String login, String password, String name, String lastName, String email){
        super(id, login, password, name, lastName, email);
    }

    public ArrayList<MentorModel> getMentors(){
        return mentors;
    }

    public void addMentor(MentorModel mentor){
        mentors.add(mentor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminModel that = (AdminModel) o;
        return Objects.equals(mentors, that.mentors);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mentors);
    }
}
