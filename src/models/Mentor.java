package models;

public class Mentor extends User {

    private String groupResponsibility;

    public Mentor(String name, String login, String password, String email, String phoneNumber,String groupSignature) {
        super(name, login, password, email, phoneNumber);
        this.groupResponsibility = groupSignature;
    }
}
