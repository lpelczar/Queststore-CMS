package main;

import controllers.RootController;

public class Application {

    public static void main(String... args) {

        RootController rootController = new RootController();
        rootController.start();
    }
}
