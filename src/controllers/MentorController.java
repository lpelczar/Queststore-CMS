package controllers;


import models.Task;
import views.MentorView;
import dao.TaskDAO;
import models.Task;
import java.util.Date;

import java.util.InputMismatchException;

public class MentorController {
    MentorView view = new MentorView();

    private boolean isRunning = true;

    public void start(){
        int option = 0;

        while (isRunning) {
            view.handleMentorMenu();

            try {
                option = view.askForOption();
            }
            catch (InputMismatchException e) {
                System.err.println("You type wrong sign!");
            }

            if (option == 1) {
                //Promote blank user to Student, add argument (list with users) to display method
                //view.displayBlankUsers();
                //String userLogin = view.askForLoginToPromote();
                ;
            }
            else if (option == 2) {
                // Add new task
                createTask();
            }
            else if (option == 3) {
                // Add new item
                createItem();
            }
            else if (option == 4) {
                // Edit quest
                ;
            }
            else if (option == 5) {
                // Edit item
                ;
            }
            else if (option == 6) {
                // Mark Student's task
                ;
            }
            else if (option == 7) {
                // Mark Students's bought items
                ;
            }
            else if (option == 8) {
                // See Student's backpack
                ;
            }
            else if (option == 9) {
                isRunning = false;
            }
        }
    }

    public void createTask() {

        TaskDAO taskdao = new TaskDAO();
        view.displayCreatingTask();
        String name = view.askForInput();
        String category = view.askForCategory();
        String description = view.askForInput();
        Date deadline = view.askForDeadline();
        int points = 0;
        try {
            points = view.askForPoints();
        }
        catch (InputMismatchException e) {
            System.err.println("You type wrong sign!");
        }
        Task task = new Task(name, category, description, deadline, points);

        taskdao.addTask(task);

    }

    public void createItem() {

        view.displayCreatingItem();
        String name = view.askForInput();
        int price = 0;
        try {
            price = view.askForPrice();
        }
        catch (InputMismatchException e) {
            System.err.println("You type wrong sign!");
        }
        String description = view.askForInput();

        //we need dao for shop!

    }
}
