package handlers;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RootHandler {

    public void start() throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(7000), 0);
        server.createContext("/login", new LoginHandler());
        server.createContext("/static", new StaticHandler());
        server.createContext("/admin", new AdminHandler());
        server.setExecutor(null);
        server.start();
    }
}
