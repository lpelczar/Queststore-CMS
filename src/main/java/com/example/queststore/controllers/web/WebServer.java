package com.example.queststore.controllers.web;

import com.example.queststore.dao.UserDAO;
import com.example.queststore.data.DbHelper;
import com.example.queststore.data.sessions.SessionDAO;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {

    private UserDAO userDAO;
    private SessionDAO sessionDAO;

    public WebServer(UserDAO userDAO, SessionDAO sessionDAO) {
        this.userDAO = userDAO;
        this.sessionDAO = sessionDAO;
    }

    public void start() throws IOException {

        checkDatabaseSetup();
        HttpServer server = HttpServer.create(new InetSocketAddress(7000), 0);
        server.createContext("/register", new RegistrationHandler(userDAO));
        server.createContext("/login", new LoginHandler(userDAO, sessionDAO));
        server.createContext("/static", new StaticHandler());
        server.createContext("/admin", new AdminHandler());
        server.createContext("/mentor", new MentorHandler());
        server.setExecutor(null);
        server.start();
    }

    private void checkDatabaseSetup() {
        DbHelper dbHelper = new DbHelper();
        if (!dbHelper.isDatabaseFileExists()) {
            dbHelper.setDatabasePath("queststore.db");
            dbHelper.createDatabase();
            dbHelper.runSqlScriptsFromFile("InsertFakeData.sql");
        }
    }
}
