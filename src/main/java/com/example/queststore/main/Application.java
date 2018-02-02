package main.java.com.example.queststore.main;

import main.java.com.example.queststore.controllers.RootController;

public class Application {

    public static void main(String... args) {

        RootController rootController = new RootController();
        rootController.start();
    }
}
