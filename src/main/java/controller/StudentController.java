package controller;

import dao.*;
import model.WalletModel;
import utils.InputGetter;
import view.StudentView;
import model.StudentModel;

class StudentController {

    private StudentDB studentDB;
    private WalletDBImplement walletDB;
    private StudentView view;
    private final String HEADER = "======= HELLO-STUDENT =======\n";
    private final String[] OPTIONS = {"Display my profile",
                                      "Display my items",
                                      "Display quests",
                                      "Edit my profile",
                                      "Buy artifact",
                                      "Exit"};

    StudentController() {
        this.studentDB = new StudentDBImplement();
        this.walletDB = new WalletDBImplement();
        this.view = new StudentView();
    }

    void run(String id) {
        StudentModel student = studentDB.loadStudent(Integer.valueOf(id));
        WalletModel wallet = walletDB.loadWalletModel(Integer.valueOf(id));
        Integer option = 1;
        while (!(option == 6)) {
            view.displayMenu(HEADER, OPTIONS);
            option = InputGetter.getNumber("Choose option: ");
            switch (option) {
                case 1:
                    view.displayStudentData(student.toString());
                    InputGetter.getString();
                    break;
                case 2:
                    view.displayText(wallet.toString());
                    view.displayListOfObjects(wallet.getArtefacts());
                    InputGetter.getString();
                    break;
                case 3:
                    //this.displayQuests();
                    break;
                case 4:
                    this.editProfile(student);
                    studentDB.exportStudent(student);
                    break;
                case 5:

                    break;
                case 6:
                    view.displayText("Good bye");
                    break;
            }
        }
    }

    private void editProfile(StudentModel student) {
        String[] options = {"Login", "Password", "Name", "Surname", "Email", "Exit"};
        String header = "Edit:";
        int option = 1;

        while (!(option == 6)) {
            view.displayMenu(header, options);
            option = InputGetter.getNumber("Choose option: ");
            switch (option) {
                case 1:
                    view.displayText(student.getLogin());
                    String newLogin = InputGetter.getString("Type new login");
                    student.setLogin(newLogin);
                    break;
                case 2:
                    view.displayText(student.getPassword());
                    String newPassword = InputGetter.getString("Type new password");
                    student.setPassword(newPassword);
                    break;
                case 3:
                    view.displayText(student.getName());
                    String newName = InputGetter.getString("Type new Name");
                    student.setName(newName);
                    break;
                case 4:
                    view.displayText(student.getLastName());
                    String newLastName = InputGetter.getString("Type new Last name");
                    student.setLastName(newLastName);
                    break;
                case 5:
                    view.displayText(student.getEmail());
                    String newEmail = InputGetter.getString("Type new Email");
                    student.setEmail(newEmail);
                    break;
            }
        }
    }
}