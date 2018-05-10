package com.example.queststore.controllers.web;

import com.example.queststore.controllers.console.TeamController;
import com.example.queststore.dao.sqlite.SqliteStudentDataDAO;
import com.example.queststore.dao.sqlite.SqliteStudentItemDAO;
import com.example.queststore.views.console.UserView;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

class TeamHandler {

    private HttpExchange httpExchange;
    private int mentorId;

    TeamHandler(HttpExchange httpExchange, int mentorId) {
        this.httpExchange = httpExchange;
        this.mentorId = mentorId;
    }

    void reshuffleTeams() throws IOException {
        TeamController teamController = new TeamController(new SqliteStudentDataDAO(),
                new SqliteStudentItemDAO(), new UserView());
        teamController.handleReshuffleStudentsTeams();
        redirectToPath(httpExchange, "/mentor/" + mentorId + "/students" );
    }

    private void redirectToPath(HttpExchange httpExchange, String path) throws IOException {
        httpExchange.getResponseHeaders().add("Location", path);
        httpExchange.sendResponseHeaders(301, -1);
    }
}
