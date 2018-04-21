package queststore.controllers;


import queststore.dao.*;
import queststore.data.contracts.UserEntry;
import queststore.models.StudentData;
import queststore.models.User;
import queststore.services.*;
import queststore.views.MentorView;
import queststore.views.UserView;

public class MentorController extends UserController {

    private MentorView mentorView;
    private TeamService teamService;
    private GroupService groupService;
    private TaskService taskService;
    private StudentService studentService;
    private ItemService itemService;

    public MentorController(UserDAO userDAO, UserView view, StudentDataDAO studentDataDAO, MentorView mentorView,
                            TeamService teamService, GroupService groupService, TaskService taskService,
                            StudentService studentService, ItemService itemService) {
        super(userDAO, view, studentDataDAO);
        this.mentorView = mentorView;
        this.teamService = teamService;
        this.groupService = groupService;
        this.taskService = taskService;
        this.studentService = studentService;
        this.itemService = itemService;
    }

    public void start() {
        int option;
        boolean isAppRunning = true;

        while (isAppRunning) {
            mentorView.clearConsole();
            mentorView.handleMentorMenu();
            option = mentorView.askForOption();

            switch (option) {
                case 1:
                    promoteBlankUser();
                    break;
                case 2:
                    groupService.addStudentToGroup();
                    break;
                case 3:
                    taskService.addNewQuest();
                    break;
                case 4:
                    itemService.addNewItem();
                    break;
                case 5:
                    taskService.editQuest();
                    break;
                case 6:
                    itemService.editItem();
                    break;
                case 7:
                    taskService.markStudentAchievedQuest();
                    break;
                case 8:
                    itemService.markStudentUsedItem();
                    break;
                case 9:
                    studentService.showStudentSummary();
                    break;
                case 10:
                    teamService.handleReshuffleStudentsTeams();
                    break;
                case 11:
                    isAppRunning = false;
            }
        }
    }

    @Override
    void promote(User user) {
        user.setRole(UserEntry.STUDENT_ROLE);
        boolean isPromoted = getUserDAO().update(user);
        StudentData student = createStudent(user);
        getStudentDataDAO().add(student);
        if (isPromoted) {
            mentorView.displayHasBeenPromoted();
        } else {
            mentorView.displayUserNotExists();
        }
    }
}
