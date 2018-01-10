package models;

public class Mentor extends User {
    String groupResponsibility;

    public Mentor(String groupSignature) {
        super(name, login, password, email, phoneNumber);
        this.groupResponsibility = groupSignature;
    }
}
