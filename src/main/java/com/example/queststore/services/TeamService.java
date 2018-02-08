package com.example.queststore.services;

import com.example.queststore.dao.DbStudentDataDAO;
import com.example.queststore.models.StudentData;
import com.example.queststore.views.UserView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TeamService {
    private DbStudentDataDAO dbStudentDataDAO = new DbStudentDataDAO();
    private UserView view = new UserView();

    public void handleRerollStudentsTeams() {
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
        List<StudentData> studentsWithoutTeams = setAllStudentsNotAssignToTeam(students);

        if (numberOfTeams > 1) {
            return assignToRandomTeams(studentsWithoutTeams, numberOfTeams);
        }
        else {
            return assignAllToOneTeam(studentsWithoutTeams);
        }
    }

    private List<StudentData> setAllStudentsNotAssignToTeam(List<StudentData> students) {
        for (StudentData student : students) {
            student.setTeamName(null);
        }
        return students;
    }

    private List<StudentData> assignToRandomTeams(List<StudentData> students, int numberOfTeams) {
        Random randomNumber = new Random();
        boolean isAllAssigned = false;
        int index = 0;

        while (!isAllAssigned) {
            int randomIndex = randomNumber.nextInt(numberOfTeams);
            String randomTeam = String.valueOf(convertNumberToChar(randomIndex));

            if (isPossibilityToAssign(randomTeam)) {
                students.get(index).setTeamName(randomTeam);

                isAllAssigned = checkIfAllStudentsHaveTeam(students);
                ++index;
            }
        }
        return students;
    }

    private boolean isPossibilityToAssign(String randomTeam) {
        final int MAX = 3;
        Map<String, Integer> usedPossibilities = new HashMap<>();

        if (!usedPossibilities.containsKey(randomTeam)) {
            usedPossibilities.put(randomTeam, 1);
        }
        else if (usedPossibilities.get(randomTeam) < MAX) {
            usedPossibilities.put(randomTeam, usedPossibilities.get(randomTeam) + 1);
        }
        else {
            return false;
        }
        return true;
    }

    private boolean checkIfAllStudentsHaveTeam(List<StudentData> students) {
        for (StudentData student : students) {
            if (student.getTeamName() == null) return false;
        }
        return true;
    }

    private List<StudentData> assignAllToOneTeam(List<StudentData> students) {
        int INDEX_OF_TEAM = 0;

        for (StudentData student : students) {
            String team = String.valueOf(convertNumberToChar(INDEX_OF_TEAM));
            student.setTeamName(team);
        }
        return students;
    }

    private void updateDbStudentsTeam(List<StudentData> students) {
        boolean isUpdated = false;

        for (StudentData student : students) {
            isUpdated = dbStudentDataDAO.updateStudentData(student);
        }

        if (isUpdated) {
            view.clearConsole();
            view.displayOperationSuccessfull();
            view.displayPressAnyKeyToContinueMessage();
        }
    }
}
