package handlers;

import com.sun.net.httpserver.HttpServer;
import dao.LoginDAO;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RootHandler {

    private LoginDAO loginDAO;

    public RootHandler(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public void start() throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(7000), 0);
        server.createContext("/login", new LoginHandler(loginDAO));
        server.createContext("/static", new StaticHandler());
        server.createContext("/admin", new AdminHandler());
        server.createContext("/mentor", new MentorHandler());
        server.setExecutor(null);
        server.start();
    }
}
