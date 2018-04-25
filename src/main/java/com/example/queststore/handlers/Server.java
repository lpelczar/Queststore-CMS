package com.example.queststore.handlers;

import com.example.queststore.dao.UserDAO;
import com.sun.net.httpserver.HttpServer;
import com.example.queststore.data.sessiondatabase.SessionDAO;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    private UserDAO userDAO;
    private SessionDAO sessionDAO;

    public Server(UserDAO userDAO, SessionDAO sessionDAO) {
        this.userDAO = userDAO;
        this.sessionDAO = sessionDAO;
    }

    public void start() throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(7000), 0);
        server.createContext("/login", new LoginHandler(userDAO, sessionDAO));
        server.createContext("/static", new StaticHandler());
        server.createContext("/admin", new AdminHandler());
        server.createContext("/mentor", new MentorHandler());
        server.setExecutor(null);
        server.start();
    }
}
