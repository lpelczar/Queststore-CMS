package views;
import models.Team;

import java.util.*;

public class MentorView extends UserView {

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

    public void displayMentorMenu() {
        for (Integer option : menu.keySet()) {
            System.out.println(option + ". " + menu.get(option) + "\n");
        }
    }

    public void handleMentorMenu() {
        prepareMentorMenu();
        displayMentorMenu();
    }

    public void displayTeams(List<Team> teams) {
        for (Team team : teams) System.out.println(team.getTeamName());
    }
    
    public void displayStudentsInGroup(Group group) {
        for (Student student : group.getAllStudents()) System.out.println(student.toString());
    }
    
    public void displaySummaryOfStudentsCoins(List<Student> students) {
        for (Student student : students) System.out.println(student.toString());
    }
    
    public void displayStudentsItemsList(List<Student> students) {
        for (Student student : students) System.out.println(student.backpackToString());//?
    }
    
    public void displayCreatingTask() {
        System.out.println("Create new task:\nName:");
    }
    
    public String askForCategory() {
        char option = "q";

        while() {
            System.out.println("\nBasic or extra(b/e):");
            option = scanner.next();
            if (option == "b") return "Basic";
            else if (option == "e") return "Extra";
        }
    }
    
    public Date askForDeadline() {
        System.out.println("\nDeadline:");
        try {
            return new Date(askForOption(), askForOption(), askForOption());
        }
        catch (InputMismatchException e) {
            System.err.println("You type wrong sign!");
        }
    
    protected int askForPoints() throws InputMismatchException {

        int points = 0;

        while(!scanner.hasNextInt()) {
            System.out.println("\nPoints:");
            points = scanner.nextInt();
        }
        return points;
    }

}
