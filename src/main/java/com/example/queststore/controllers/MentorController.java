package com.example.queststore.controllers;


import com.example.queststore.dao.DbItemDAO;
import com.example.queststore.dao.DbStudentDataDAO;
import com.example.queststore.dao.DbUserDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.Item;
import com.example.queststore.models.StudentData;
import com.example.queststore.models.User;
import com.example.queststore.views.MentorView;

import java.util.*;

public class MentorController extends UserController {

    private MentorView view = new MentorView();
    private UserDAO dbUserDAO = new DbUserDAO();
    private DbItemDAO dbItemDAO = new DbItemDAO();
    private DbStudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();

    public void start(){
        int option;
        boolean isAppRunning = true;

        while (isAppRunning) {
            view.clearConsole();
            view.handleMentorMenu();
            option = view.askForOption();

            if (option == 1) {
                promoteBlankUser();
            } else if (option == 2) {
//                addStudentToGroup();
            } else if (option == 3) {
//                addNewQuest();
            } else if (option == 4) {
                addNewItem();
            } else if (option == 5) {
//                editQuest();
            } else if (option == 6) {
                editItem();
            } else if (option == 7) {
//                markStudentQuest();
            } else if (option == 8) {
//                markStudentItem();
            } else if (option == 9) {
//                showStudentSummary();
            } else if (option == 10) {
                hadnleRerollStudentsTeams();
            } else if (option == 11) {
                isAppRunning = false;
            }
        }
    }

    @Override
    void promote(User user) {

        user.setRole(UserEntry.STUDENT_ROLE);
        boolean isPromoted = dbUserDAO.update(user);

        StudentData student = createStudent(user);
        dbStudentDataDAO.add(student);

        if (isPromoted) {
            view.displayHasBeenPromoted();
        } else {
            view.displayUserNotExists();
        }
    }

    private void addNewItem() {
        DbItemDAO dbItemDAO = new DbItemDAO();
    
        view.clearConsole();
        view.displayCreatingItem();
        String name = view.askForString();

        int price = priceCheck();

        String category = view.askForItemCategory();

        view.displayUpdateDescription();
        String description = view.askForString();

        Item item = new Item(name, price, description, category);

        if (dbItemDAO.addItem(item)) {
            view.displayOperationSuccessful();
        }
        else {
            view.displayOperationFailed();
        }
    }

  private void editItem() {
        view.clearConsole();

        List<Item> items = dbItemDAO.getAllItems();
        view.displayItemsInStore(items);
        int id = view.askForInt();

        view.clearConsole();

        Item item = dbItemDAO.getItemById(id);
        view.displayItemInfo(item);

        int updateOption = view.askForChange(item);
        handleUpdateBonus(updateOption, item);

    }

    private void handleUpdateBonus(int updateOption, Item item) {
        int UPDATE_NAME = 1;
        int UPDATE_PRICE = 2;
        int UPDATE_CATEGORY = 3;
        int UPDATE_DESCRIPTION = 4;

        if (updateOption == UPDATE_NAME) {
            view.displayUpdateName();
            item.setName(view.askForString());
        }
        else if (updateOption == UPDATE_PRICE) {
            view.displayUpdatePrice();
            item.setPrice(view.askForInt());
        }
        else if (updateOption == UPDATE_CATEGORY) {
            item.setCategory(view.askForItemCategory());
        }
        else if (updateOption == UPDATE_DESCRIPTION) {
            view.displayUpdateDescription();
            item.setDescription(view.askForString());
        }
        else {
            view.displayOperationFailed();
        }

        boolean isUpdate = dbItemDAO.updateItem(item);
        if (isUpdate) {
            view.displayOperationSuccessful();
        }
    }

    private Integer priceCheck() {
        Integer price = 0;
        boolean incorrect = true;

        try {
            while(incorrect) {
                price = view.askForPrice();
                if (price != null) {
                    incorrect = false;
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("You type wrong sign!");
        }
        return price;
    }

    private void hadnleRerollStudentsTeams() {
        List<StudentData> students = dbStudentDataDAO.getAllStudents();
        List<StudentData> teams = rerollStudentsTeam(students);
        updateDbStudentsTeam(teams);
    }

    private List<StudentData> rerollStudentsTeam(List<StudentData> students) {
        int numberOfTeams = countNumbersOfTeams(students);
        return assignStudentsToTeams(students, numberOfTeams);
    }

    private int countNumbersOfTeams(List<StudentData> students) {
        final int NUMBER_OF_TEAM_MEMBERS = 3;
        final int createTeam = 1;
        int numberOfStudents = students.size();
        int numberOfTeams;

        if (sizeIsEven(numberOfStudents, NUMBER_OF_TEAM_MEMBERS)) {
            numberOfTeams = numberOfStudents / NUMBER_OF_TEAM_MEMBERS;
        }
        else {
            numberOfTeams = numberOfStudents / NUMBER_OF_TEAM_MEMBERS + 1;
        }

        if (numberOfTeams < 1) numberOfTeams = createTeam;

        return numberOfTeams;
    }

    private boolean sizeIsEven(int numberOfStudents, int NUMBER_OF_TEAM_MEMBERS) {
        return numberOfStudents % NUMBER_OF_TEAM_MEMBERS == 0;
    }

    private char convertNumberToChar(int number) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return alphabet.charAt(number);
    }

    private List<StudentData> assignStudentsToTeams(List<StudentData> students, int numberOfTeams) {
        if (numberOfTeams > 1) {
            return assingToRandomTeams();
        }
        else {
            return assingAllToOneTeam(students);
        }
    }

    private List<StudentData> assingAllToOneTeam(List<StudentData> students) {
        int INDEX_OF_TEAM = 0;

        for (StudentData student : students) {
            String team = String.valueOf(convertNumberToChar(INDEX_OF_TEAM));
            student.setTeamName(team);
        }
        return students;
    }

    private void updateDbStudentsTeam(List<StudentData> students) {
        for (StudentData student : students) {
            dbStudentDataDAO.updateStudentData(student);
        }
    }
}
