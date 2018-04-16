package controller;

import dao.OpenCloseConnectionWithDB;
import view.RootView;

import java.sql.Connection;


public class RootController{

    private RootView rootView = new RootView();
    private OpenCloseConnectionWithDB connectionWithDB = new OpenCloseConnectionWithDB();
    private LoginDBController loginController = new LoginDBController();

    public void startApplication(){
 
        rootView.displayMenu();
        String[] idAndRole = loginController.processValidation();
        String id = String.valueOf(idAndRole[0]);

        String option = String.valueOf(idAndRole[1]);
        switch (option) {
            case "1":
                AdminController adminController = new AdminController();
                adminController.run(id);
                break;
            case "2":
                MentorController mentorController = new MentorController();
                mentorController.run(id);
                break;
            case "3":
                StudentController studentController = new StudentController();
                studentController.run(id);
                break;
        }
    }
}
