package controllers;

import views.RootView;

public class RootController {

    private UsersDAO usersDAO;
    private RootView rootView;

    RootController() {

        this.usersDAO = new UsersDAO();
        this.rootView = new RootView();
    }

    public void start() {
        boolean shouldExit = false;

        while (!shouldExit) {
            rootView.displayMenu();
            String userInput = rootView.getUserInput();
            switch (userInput) {
                case "0":
                    shouldExit = true;
                    break;
                default:
                    rootView.displayWrongInputMessage();
            }
        }
    }


}
