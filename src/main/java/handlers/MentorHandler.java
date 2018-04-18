package handlers;

import com.google.common.io.Resources;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.LoginDAO;
import dao.SqliteLoginDAO;
import data.sessiondatabase.Session;
import data.sessiondatabase.SessionDAO;
import data.sessiondatabase.SqliteSessionDAO;
import model.database.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.URL;

public class MentorHandler implements HttpHandler {

    private SessionDAO sessionDAO = new SqliteSessionDAO();
    private LoginDAO loginDAO = new SqliteLoginDAO();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        HttpCookie cookie;

        if (method.equals("GET")) {
            String sessionCookie = httpExchange.getRequestHeaders().getFirst("Cookie");
            if (sessionCookie != null) {
                cookie = HttpCookie.parse(sessionCookie).get(0);
                if (sessionDAO.getById(cookie.getValue()) != null) {
                    showMentorPage(httpExchange, cookie);
                    return;
                }
            }
            redirectToLogin(httpExchange);
        }

        if (method.equals("POST")) {
            // TODO 1: post methods
        }
    }

    private void redirectToLogin(HttpExchange httpExchange) throws IOException {
        Headers headers = httpExchange.getResponseHeaders();
        String redirect = "/login";
        headers.add("Location", redirect);
        httpExchange.sendResponseHeaders(301, -1);
    }

    private void showMentorPage(HttpExchange httpExchange, HttpCookie cookie) throws IOException {

        String path = httpExchange.getRequestURI().getPath();
        System.out.println("Path: " + path);

        String sessionId = cookie.getValue();
        Session session = sessionDAO.getById(sessionId);
        int userId = session.getUserId();
        User user = loginDAO.getById(userId);

        if (path.contains("add")) {
            URL fileURL = Resources.getResource("static/mentor/add_quest.html");
            StaticHandler.sendFile(httpExchange, fileURL);
        } else {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor_manager.twig");
            JtwigModel model = JtwigModel.newModel();
            model.with("userName", user.getLogin());
            String response = template.render(model);
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
