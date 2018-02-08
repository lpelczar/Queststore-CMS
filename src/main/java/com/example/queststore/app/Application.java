package com.example.queststore.app;

import com.example.queststore.controllers.RootController;

public class Application {

    public static void main(String... args) {

        RootController rootController = new RootController();
        rootController.start();
    }
}
