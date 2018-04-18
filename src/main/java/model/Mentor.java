package model;

public class Mentor {

    private int mentor_id;
    private String name;
    private String lastName;
    private String email;

    public Mentor(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public Mentor(int mentor_id, String name, String lastName, String email) {
        this.mentor_id = mentor_id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public int getMentor_id() {
        return mentor_id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
