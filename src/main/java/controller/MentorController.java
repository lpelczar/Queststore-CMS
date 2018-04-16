package controller;

import model.GroupModel;
import dao.LoginDB;
import dao.LoginDBImplement;
import dao.StudentDB;
import dao.StudentDBImplement;
import model.StudentModel;
import utils.InputGetter;
import view.MentorView;

import java.util.ArrayList;

public class MentorController {
    private LoginDB loginDB;
    private StudentDB studentDB;
    private QuestController quest;
    private MentorView mentorView;
    private String HEADER = "======= HELLO-MENTOR =======\n";
    private String HEADER2 = "Choose what atribute you want to edit";
    private ArrayList<StudentModel> existingStudents;
    private final String[] OPTIONS = {  "Create Student",
                                        "Edit Student",
                                        "Display students in my group",
                                        "Option for quest",
                                        "Option for artifacts",
                                        "Exit"};
    private final String[] OPTIONS2 = {"Login", "Password", "Name",
                                        "Surname", "Email"};


    MentorController() {
        this.loginDB = new LoginDBImplement();
        this.quest = new QuestController();
        this.studentDB = new StudentDBImplement();
        // this.wallet = new WalletModel();
        this.mentorView = new MentorView();
        this.existingStudents = studentDB.getAllStudents();
    }

    void run(String id) {
        GroupModel mentorGroup = studentDB.getMentorGroupByMentorID(id);
        Integer option = 1;

        while (!(option == 6)) {
            mentorView.displayMenu(HEADER, OPTIONS);
            option = InputGetter.getNumber("Choose option: ");

            switch (option) {
                case 1:
                    createStudent(studentDB, mentorGroup);
                    break;
                case 2:
                    StudentModel studentToEdit = this.editStudent();
                    studentDB.exportStudent(studentToEdit);
                    break;
                case 3:
                    mentorView.displayListOfObjects(mentorGroup.getStudents());
                    break;
                case 4:
                    quest.questOption();
                    break;
                case 5:
                    mentorView.displayText("Artefacts option should be implemented here!");
                    break;
                case 6:
                    mentorView.displayText("Good bye");
                    break;
            }
        }
    }


    public void createStudent(StudentDB studentDB, GroupModel mentorGroup) {
        mentorView.displayText("This student will be added to your group, press enter to continue");
        InputGetter.getString();

        String id = loginDB.getLastId();
        String login = InputGetter.getString("Please enter student login: ");
        String password = InputGetter.getString("Please enter student password: ");
        String name = InputGetter.getString("Please enter student name: ");
        String lastName = InputGetter.getString("Please enter student lastName: ");

        StudentModel newStudent = new StudentModel(id, login, password, name, lastName);

        loginDB.saveNewUserToDatabase(newStudent);
        studentDB.insertNewStudentToGroup(Integer.valueOf(newStudent.getId()), mentorGroup.getGroupId());

        mentorView.displayText("Student created successfully");
    }

    private StudentModel editStudent() {
        boolean optionChosen = false;
        StudentModel studentToEdit = getStudent();

        while(!optionChosen) {
            mentorView.displayMenu(HEADER2, OPTIONS2);
            Integer option = InputGetter.getNumber("Enter your option");
            switch (option) {
                case 1:
                    String newLogin = InputGetter.getString("Enter new login");
                    studentToEdit.setLogin(newLogin);
                    optionChosen = true;
                    break;
                case 2:
                    String newPassword = InputGetter.getString("Enter new password");
                    studentToEdit.setPassword(newPassword);
                    optionChosen = true;
                    break;
                case 3:
                    String newName = InputGetter.getString("Enter new name");
                    studentToEdit.setName(newName);
                    optionChosen = true;
                    break;
                case 4:
                    String newLastName = InputGetter.getString("Enter new lastname");
                    studentToEdit.setLastName(newLastName);
                    optionChosen = true;
                    break;
                case 5:
                    String newEmail = InputGetter.getString("Enter new email");
                    studentToEdit.setEmail(newEmail);
                    optionChosen = true;
                    break;
            }
        }
        return studentToEdit;
    }

    public StudentModel getStudent(){
        boolean studentNotChosen = true;
        Integer studentIndex = 0;

        while(studentNotChosen){

            mentorView.displayListOfObjects(this.existingStudents);
            studentIndex = InputGetter.getNumber("Please enter a mentor number");

            if (studentIndex < this.existingStudents.size()){
                studentNotChosen = false;
            }
            else {
                mentorView.displayText("Wrong number");
            }
        }
        return this.existingStudents.get(studentIndex);
    }
}

