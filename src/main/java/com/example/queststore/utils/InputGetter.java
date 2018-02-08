package com.example.queststore.utils;

import java.util.Scanner;

import static com.example.queststore.utils.IntegerChecker.isInteger;

public class InputGetter {

    public static String getStringInputFromConsole(String message) {

        String input = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            System.out.print(message);
            input = getStringInput();
            if (input.trim().length() > 0) {
                isCorrectInput = true;
            }
        }
        return input;
    }

    public static int getIntInputFromConsole(String message) {

        String input = null;
        boolean isCorrectInput = false;

        while(!isCorrectInput) {
            System.out.print(message);
            input = getStringInput();
            if (input.trim().length() > 0 && isInteger(input)) {
                isCorrectInput = true;
            }
        }
        return Integer.parseInt(input);
    }

    private static String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
