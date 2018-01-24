package views;

import java.util.*;

public class MentorView extends UserView {

//    private Map<Integer, String> menu;
//    private Scanner scanner = new Scanner(System.in);
//
//    public void prepareMentorMenu() {
//        menu.put(1, "Promote blank user to student.");
//        menu.put(2, "Add new quest.");
//        menu.put(3, "Add new artifact to store.");
//        menu.put(4, "Edit quest.");
//        menu.put(5, "Edit artifact.");
//        menu.put(6, "Mark Codecooler's quest.");
//        menu.put(7, "Mark Codecooler's bought artifacts.");
//        menu.put(8, "See Codecooler's wallet.");
//        menu.put(9, "Log out.");
//    }
//
//    public void displayMentorMenu() {
//        for (Integer option : menu.keySet()) {
//            System.out.println(option + ". " + menu.get(option) + "\n");
//        }
//    }
//
//    public void handleMentorMenu() {
//        prepareMentorMenu();
//        displayMentorMenu();
//    }
//
//    public void displayTeams(List<Team> teams) {
//        for (Team team : teams) System.out.println(team.getTeamName());
//    }
//
//    public void displayStudentsInGroup(Team group) {
//        for (Student student : group.getAllMembers()) System.out.println(student.toString());
//    }
//
//    public void displaySummaryOfStudentsCoins(List<Student> students) {
//        for (Student student : students) System.out.println(student.toString());
//    }
//
//    public void displayStudentsItemsList(List<Student> students) {
//    }
//
//    public void displayCreatingTask() {
//        System.out.println("Create new task:\nName:");
//    }
//
//    public String askForCategory() {
//        char option = 'q';
//
//        while(true) {
//            System.out.println("\nBasic or extra(b/e):");
//            option = scanner.next().charAt(0);
//            if (option == 'b') return "Basic";
//            else if (option == 'e') return "Extra";
//        }
//    }
//
//    @SuppressWarnings("deprecation")
//    public Date askForDeadline() {
//        System.out.println("\nDeadline:");
//        try {
//            return new Date(askForOption(), askForOption(), askForOption());
//        }
//        catch (InputMismatchException e) {
//            System.err.println("You type wrong sign!");
//        }
//        return null;
//    }
//
//    public int askForPoints() throws InputMismatchException {
//
//        int points = 0;
//
//        while(!scanner.hasNextInt()) {
//            System.out.println("\nPoints:");
//            points = scanner.nextInt();
//        }
//        return points;
//    }
//
//    public void displayCreatingItem() {
//        System.out.println("Create new Item:\nName:");
//    }
//
//    public int askForPrice() throws InputMismatchException {
//
//        int price = 0;
//
//        while(!scanner.hasNextInt()) {
//            System.out.println("\nPrice:");
//            price = scanner.nextInt();
//        }
//        return price;
//    }
}
