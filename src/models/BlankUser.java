package models;

import java.io.Serializable;

public class BlankUser extends User implements Serializable {

    public BlankUser(String name, String login, String password, String email, String phoneNumber) {
        super(name, login, password, email, phoneNumber);
    }
}
