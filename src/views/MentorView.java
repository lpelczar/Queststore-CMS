package views;
import models.BlankUser;

import java.util.*;

public class MentorView {

    private Map<Integer, String> menu;
    private Scanner scanner = new Scanner(System.in);

    public void prepareMentorMenu() {
        menu.put(1, "Promote blank user to student.");
        menu.put(2, "Add new quest.");
        menu.put(3, "Add new artifact to store.");
        menu.put(4, "Edit quest.");
        menu.put(5, "Edit artifact.");
        menu.put(6, "Mark Codecooler's quest.");
        menu.put(7, "Mark Codecooler's bought artifacts.");
        menu.put(8, "See Codecooler's wallet.");
        menu.put(9, "Log out.");
    }

}
