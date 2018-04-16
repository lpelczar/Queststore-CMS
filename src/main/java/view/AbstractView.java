package view;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractView<T> {

    public void displayText(String text) {
    System.out.println(text);
  }
    public void displayInteger(int text) {
        System.out.println(text);
    }


    public void displayMenu(String header, String[] options) {
        int optionNumber = 1;
        displayText(header);

        for (String option : options) {
            displayText(optionNumber + "--->" + option);
            optionNumber ++;
        }
    }

    public void displayListOfObjects(ArrayList<T> objects){
        int index = 0;
        for (T object : objects) {
            displayText(index + "--->" + object.toString());
            index++;
        }
    }
}

