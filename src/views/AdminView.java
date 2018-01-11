package views;
import java.util.*;
import models.*;

public class AdminView extends UserView {

    private Map<Integer, String> menu = new HashMap<>();
    private Scanner scanner;

    public void prepareAdminMenu() {
        menu.put(1, "Promote blank user.");
        menu.put(2, "Edit user profile.");
        menu.put(3, "Create new group.");
        menu.put(4, "Edit level treshold.");
        menu.put(5, "Show all users.");
        menu.put(6, "Log out.");
    }

    public void displayAdminMenu() {
        for (Integer option : menu.keySet()) {
            System.out.println(option + ". " + menu.get(option));
        }
    }

    public void handleAdminMenu() {
        prepareAdminMenu();
        displayAdminMenu();
    }

    public void displayBlankUsers(List<BlankUser> blankUsers) {
        for (BlankUser user : blankUsers) {
            System.out.println(user.toString());
        }
    }

    public String askForLogin() {
        scanner = new Scanner(System.in);

        System.out.println("Enter login profile to change: ");
        String userLogin = scanner.next();
        return userLogin;
    }

    public Boolean typeOfPromotion() {
        String userChoose = "";
        String[] promoteOptions = {"m", "s"};
        scanner = new Scanner(System.in);

        System.out.println("Type 'm' if you want promote user to mentor or 's' to student");

        while (!Arrays.asList(promoteOptions).contains(userChoose.toLowerCase())) {
            userChoose = scanner.next();

            if (userChoose.equals("m")) {
                return true;
            }
            else if (userChoose.equals("s")) {
                return false;
            }
        }
        throw new InputMismatchException();
    }

        public void displayNoMentorMessage() {
            System.out.println("There is no mentor with this login!");
        }

        public void displayMentorProfile(Mentor mentor) {
            System.out.println(mentor);
        }

    public void displayMentors(List<Mentor> mentorContainer) {
        System.out.println("Mentors list: \n");

        if(!mentorContainer.isEmpty()) {
            for (Mentor mentor : mentorContainer) {
                System.out.println(mentor.toString());
            }
        } else {
            System.out.println("List is empty!");
        }
    }

    public void displayStudents(List<Student> studentContainer) {
        System.out.println("Available mentors to edit: \n");

        for (Student student : studentContainer) {
            System.out.println(student.toString());
        }
    }

    public int askForChangeInProfile(User profile) {
        System.out.println(profile.toString());
        System.out.println("What would you like to change in profile:" +
                            "\n1. Name" +
                            "\n2. Login" +
                            "\n3. Email" +
                            "\n4. Phone number");

        return askForOption();
    }

    public void displayEmptyListMsg() {
        System.out.println("List is empty!");
    }

    public void displayWrongSignError() {
        System.out.println("You type wrong sign!");
    }
}
