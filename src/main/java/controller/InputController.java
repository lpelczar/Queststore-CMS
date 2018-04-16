package controller;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class InputController{

    public static String getString(String message) {
        String input = null;
        Boolean validated = true;
        while (validated) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(message);
            input = scanner.nextLine();
            validated = validateInput(input);
        }
        return input;
    }

    public static String getString() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public static Integer getNumber(String message) {
        Boolean notDone = true;
        Integer output = 0;
        while (notDone) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println(message);
                output = scanner.nextInt();
                notDone = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input! Must be '123' format");
            }
        }
        return output;
    }

    private static Boolean validateInput(String input) {
        Pattern pattern = Pattern.compile("[!#$%&'()*+/:,;=?^_`{|}~]");
        Matcher match = pattern.matcher(input);
        Boolean result = match.find();
        if (result) {
            System.out.println("Special character in your input!");
        }
        return result;
    }

}
