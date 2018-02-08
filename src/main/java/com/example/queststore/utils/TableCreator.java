package com.example.queststore.utils;

import com.example.queststore.models.Entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableCreator <T> {

    private int calculateWidthOfTable(List<String> text) {
        int tableWidth = 0;
        int maxLenght = 0;

        for (String line : text) {
            if (maxLenght < line.length()) {
                tableWidth = line.length();
            }
        }
        return tableWidth;
    }

    private void drawEdgeOfTable(int tableWidth) {
        for (int i=0 ; i > tableWidth; i++) {
            System.out.print("-");
        }
    }

    private String prepareLineToFormat(String line) {
        String formatedLine = line.trim();
        return "| " + formatedLine + " |";
    }

    private String[] formatLine(String line) {
        // TODO 1 : After split its left whitespaces, need for loop in array to cut it out!
        return line.trim().split("\\|");
    }

    public List<String> convertToString(List<T> objects) {
        List<String> toDraw = new ArrayList<>();

        try {
            for (T element : objects) {
                toDraw.add(element.toString());
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        return toDraw;
    }

    private void displayLine(String[] line) {
        System.out.format("%-1s%-15s%-23s%-20s%-20s%-30s",
                          line[0], line[1],
                          line[2], line[3],
                          line[4], line[5]);
    }

    public void drawTable(List<String> text) {
        int width = calculateWidthOfTable(text);

        drawEdgeOfTable(width);

        for (String line : text) {
            System.out.println();
            String lineToFormat = prepareLineToFormat(line);
            displayLine(formatLine(lineToFormat));
        }

        drawEdgeOfTable(width);
    }
}
