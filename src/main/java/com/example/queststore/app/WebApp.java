package com.example.queststore.app;

import com.example.queststore.controllers.web.WebServer;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.dao.sqlite.SqliteUserDAO;
import com.example.queststore.data.sessions.SessionDAO;
import com.example.queststore.data.sessions.SqliteSessionDAO;

import java.io.IOException;

public class WebApp {

    public static void main(String[] args) throws IOException {

        UserDAO userDAO = new SqliteUserDAO();
        SessionDAO sessionDAO = new SqliteSessionDAO();

        WebServer webServer = new WebServer(userDAO, sessionDAO);
        webServer.start();
    }
}
