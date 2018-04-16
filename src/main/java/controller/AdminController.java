package controller;

import model.GroupModel;
import utils.InputGetter;
import view.AdminView;
import model.AdminModel;
import model.MentorModel;
import dao.*;

import java.util.ArrayList;
import java.util.Set;

class AdminController {

    private LoginDB loginDB;
    private AdminDB adminDB;
    private StudentDB studentDB;
    private AdminView view;
    private String HEADER2 = "Choose what attribute you want to edit";
    private final String[] OPTIONS = {"Display existing mentors", "Create Mentor",
                                      "Edit mentor", "Create new group and assign mentor to it",
                                        "Display mentor's group and his codecoolers",
                                        "Edit your own info",
                                        "Exit"};
    private final String[] OPTIONS2 = {"Login", "Password", "Name",
                                       "Surname", "Email"};

    AdminController() {
        this.loginDB = new LoginDBImplement();
        this.adminDB = new AdminDBImplement();
        this.studentDB = new StudentDBImplement();
        this.view = new AdminView();

    }

    void run(String id) {
        AdminModel admin = adminDB.loadAdmin(Integer.valueOf(id));
        this.addExistingMentors(adminDB, admin);
        Integer option = 1;

        while (!(option == 7)) {
            String HEADER = "======= HELLO-ADMIN =======\n";
            view.displayMenu(HEADER, OPTIONS);
            option = InputGetter.getNumber("Choose option: ");
            switch (option) {
                case 1:
                    view.displayListOfObjects(admin.getMentors());
                    break;
                case 2:
                    MentorModel mentor = createMentor();
                    addMentorToDb(mentor, admin);
                    break;
                case 3:
                    editMentorProfile(admin, adminDB);
                    break;
                case 4:
                    assignMentorToGroup(admin);
                    break;
                case 5:
                    displayMentorsGroup(admin, studentDB);
                    break;
                case 6:
                    editAdminInfo(admin);
                    adminDB.exportAdmin(admin);
                    break;
                case 7:
                    view.displayText("Good bye");
                    break;
            }
        }
    }

    private void editAdminInfo(AdminModel admin){
        boolean optionChosen = false;
        while(!optionChosen) {
            view.displayMenu(HEADER2, OPTIONS2);
            Integer option = InputGetter.getNumber("Enter what parameter you want to edit option");
            switch (option) {
                case 1:
                    String currentLogin = admin.getLogin();
                    String enteredLogin = InputGetter.getString("Enter your current login");
                    if(currentLogin.equals(enteredLogin)){
                        String newLogin = InputGetter.getString("Now enter your new login");
                        admin.setLogin(newLogin);
                        optionChosen = true;
                        break;
                    }
                    else {
                        view.displayText("Bad login, press enter to continue");
                        InputGetter.getString();
                        break;
                    }
                case 2:
                    String currentPassword = admin.getPassword();
                    String enteredPassword = InputGetter.getString("Enter your current password");
                    if(currentPassword.equals(enteredPassword)){
                        String newPassword = InputGetter.getString("Now enter your new password");
                        admin.setPassword(newPassword);
                        optionChosen = true;
                        break;
                    }
                    else {
                        view.displayText("Bad password, press enter to continue");
                        InputGetter.getString();
                        break;
                    }
                case 3:
                    String newName = InputGetter.getString("Enter new name");
                    admin.setName(newName);
                    optionChosen = true;
                    break;
                case 4:
                    String newLastName = InputGetter.getString("Enter new lastname");
                    admin.setLastName(newLastName);
                    optionChosen = true;
                    break;
                case 5:
                    String newEmail = InputGetter.getString("Enter new email");
                    admin.setEmail(newEmail);
                    optionChosen = true;
                    break;
            }
        }
    }

    private void addExistingMentors(AdminDB database, AdminModel admin) {
        ArrayList<String[]> loginsInfo = database.getMentorsDataFromDatabase(2);
        for (String[] userInfo : loginsInfo) {
            MentorModel mentorToAdd;
            String id = userInfo[0];
            String login = userInfo[1];
            String password = userInfo[2];
            String name = userInfo[3];
            String lastName = userInfo[4];
            String email = userInfo[5];
            mentorToAdd = new MentorModel(id, login, password, name, lastName, email);
            admin.addMentor(mentorToAdd);
        }
    }

    private MentorModel createMentor() {
        String login = InputGetter.getString("Please enter mentor login: ");
        String password = InputGetter.getString("Please enter mentor password: ");
        String name = InputGetter.getString("Please enter mentor name: ");
        String lastName = InputGetter.getString("Please enter mentor lastName: ");

        return new MentorModel(login, password, name, lastName);
    }

    private void addMentorToDb(MentorModel mentor, AdminModel admin) {
        admin.getMentors().add(mentor);
        loginDB.saveNewUserToDatabase(mentor);
        view.displayText("Mentor created successfully");
    }

     private MentorModel getMentor(AdminModel admin){
       boolean mentorNotChosen = true;
       Integer mentorIndex = 0;

       while(mentorNotChosen){

           view.displayListOfObjects(admin.getMentors());
           mentorIndex = InputGetter.getNumber("Please enter a mentor number");

           if (mentorIndex < admin.getMentors().size()){
               mentorNotChosen = false;
           }
           else {
             view.displayText("Wrong number");
           }
       }
       return admin.getMentors().get(mentorIndex);
     }

     private void displayMentorsGroup(AdminModel admin, StudentDB studentDB){
        view.displayText("Choose admin to show his class (press enter to continue)");
        InputGetter.getString();

        MentorModel mentorToShow = getMentor(admin);
        String mentorId = mentorToShow.getId();
        GroupModel groupToShow = studentDB.getMentorGroupByMentorID(mentorId);

        view.displayText(mentorToShow.getName() + " " + mentorToShow.getLastName() + " Group:");
        view.displayListOfObjects(groupToShow.getStudents());
     }

     private void editMentorProfile(AdminModel admin, AdminDB database) {
       boolean optionChosen = false;

       while(!optionChosen) {
           MentorModel mentorToEdit = getMentor(admin);
           String mentorId = mentorToEdit.getId();

           view.displayMenu(HEADER2, OPTIONS2);
           Integer option = InputGetter.getNumber("Enter your option");

           switch (option) {
               case 1:
                 String newLogin = InputGetter.getString("Enter new login");
                 mentorToEdit.setLogin(newLogin);
                 database.updateUserLogin(newLogin, mentorId);
                 optionChosen = true;
                 break;

               case 2:
                 String newPassword = InputGetter.getString("Enter new password");
                 mentorToEdit.setPassword(newPassword);
                 database.updateUserPassword(newPassword, mentorId);
                 optionChosen = true;
                 break;

               case 3:
                 String newName = InputGetter.getString("Enter new name");
                 mentorToEdit.setName(newName);
                 database.updateMentorsName(newName, mentorId);
                 optionChosen = true;
                 break;

               case 4:
                 String newLastName = InputGetter.getString("Enter new last name");
                 mentorToEdit.setLastName(newLastName);
                 database.updateMentorsLastName(newLastName, mentorId);
                 optionChosen = true;
                 break;

               case 5:
                 String newEmail = InputGetter.getString("Enter new email");
                 mentorToEdit.setEmail(newEmail);
                 database.updateMentorsEmail(newEmail, mentorId);
                 optionChosen = true;
                 break;
         }
       }
     }

 private void assignMentorToGroup(AdminModel admin){
        boolean mentorNotChosen = true;
        String mentorId = null;
        ArrayList<String> mentorsIdsWithGroups = adminDB.getIdsOfMentorsHavingGroupsAlready();

        while (mentorNotChosen) {
            MentorModel mentorToAssign = getMentor(admin);
            mentorId = mentorToAssign.getId();
            if(mentorsIdsWithGroups.contains(mentorId)){
                view.displayText("This Mentor have group already, " +
                        "please choose another one or create new one");
            }
            else
                {mentorNotChosen = false;}
        }
           String newGroup = this.setGroupForMentor(loginDB.getExistingGroups());
           adminDB.createNewGroupAndAssignMentorToIt(newGroup, mentorId);
           view.displayText("Mentor assigned successfully");
 }

    private String setGroupForMentor(Set<String> existingGroups) {
        String group = null;
        boolean groupNotChosen = true;
        while (groupNotChosen) {
            group = InputGetter.getString("Enter a group you want to create");
            if (existingGroups.contains(group)) {
                view.displayText("There is already group like this, create new group");
                }
                else {
                groupNotChosen = false;
            }
        }
        return group;
    }
}
