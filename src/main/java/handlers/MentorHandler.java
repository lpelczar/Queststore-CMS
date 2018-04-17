package handlers;

import com.google.common.io.Resources;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URL;

public class MentorHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        URL fileURL = Resources.getResource("static/mentor_manager.html");
        StaticHandler.sendFile(httpExchange, fileURL);
    }
}
