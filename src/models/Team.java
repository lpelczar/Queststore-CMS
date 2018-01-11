package models;
import java.io.Serializable;
import java.util.*;

public class Team implements Serializable {
    private List<Student> teamMembers;
    private String teamSignature;

    public Team(String signature) {
        this.teamSignature = signature;
        this.teamMembers = new ArrayList<>();
    }

    public boolean addStudent(Student student) {
        if (!teamMembers.contains(student)) {
            teamMembers.add(student);
            return true;
        }
        return false;
    }

    public boolean removeStudent(Student student) {
        if (teamMembers.contains(student)) {
            teamMembers.remove(student);
            return true;
        }
        return false;
    }

    public List<Student> getAllMembers() {
        return teamMembers;
    }
    
    public String getTeamName() {
    return this.teamSignature;
    }
}
