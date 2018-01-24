package controllers;


import java.util.InputMismatchException;

public class MentorController {
//    MentorView view = new MentorView();
//
//    private boolean isRunning = true;

//    public void start(){
//
//        while (isRunning) {
//            view.handleMentorMenu();
//            int option = 0;
//            try {
//                option = view.askForOption();
//            } catch (InputMismatchException e) {
//                System.err.println("You type wrong sign!");
//            }
//
//            if (option == 1) {
//            } else if (option == 2) {
//                createTask();
//            } else if (option == 3) {
//                createItem();
//            } else if (option == 4) {
//            } else if (option == 5) {
//            } else if (option == 6) {
//            } else if (option == 7) {
//            } else if (option == 8) {
//            } else if (option == 9) {
//                isRunning = false;
//            }
//        }
//    }
//
//    public void createTask() {
//
//        TaskDAO taskdao = new TaskDAO();
//        view.displayCreatingTask();
//        String name = view.askForInput();
//        String category = view.askForCategory();
//        String description = view.askForInput();
//        Date deadline = view.askForDeadline();
//        int points = 0;
//        try {
//            points = view.askForPoints();
//        }
//        catch (InputMismatchException e) {
//            System.err.println("You type wrong sign!");
//        }
//        Task task = new Task(name, category, description, deadline, points);
//
//        taskdao.addTask(task);
//
//    }
//
//    public void createItem() {
//
//        view.displayCreatingItem();
//        String name = view.askForInput();
//        int price = 0;
//        try {
//            price = view.askForPrice();
//        }
//        catch (InputMismatchException e) {
//            System.err.println("You type wrong sign!");
//        }
//        String description = view.askForInput();
//    }
}
