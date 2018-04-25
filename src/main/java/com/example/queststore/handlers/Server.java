package com.example.queststore.handlers;

import com.sun.net.httpserver.HttpServer;
import dao.LoginDAO;
import com.example.queststore.data.sessiondatabase.SessionDAO;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    private LoginDAO loginDAO;
    private SessionDAO sessionDAO;

    public Server(LoginDAO loginDAO, SessionDAO sessionDAO) {
        this.loginDAO = loginDAO;
        this.sessionDAO = sessionDAO;
    }

    public void start() throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(7000), 0);
        server.createContext("/login", new LoginHandler(loginDAO, sessionDAO));
        server.createContext("/static", new StaticHandler());
        server.createContext("/admin", new AdminHandler());
        server.createContext("/mentor", new MentorHandler());
        server.setExecutor(null);
        server.start();
    }
}
