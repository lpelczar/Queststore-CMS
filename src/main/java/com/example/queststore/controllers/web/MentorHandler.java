package com.example.queststore.controllers.web;

import com.example.queststore.dao.TaskDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.dao.sqlite.SqliteTaskDAO;
import com.example.queststore.dao.sqlite.SqliteUserDAO;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.data.sessions.Session;
import com.example.queststore.data.sessions.SessionDAO;
import com.example.queststore.data.sessions.SqliteSessionDAO;
import com.example.queststore.models.User;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.URL;

import static com.example.queststore.controllers.web.MentorOptions.PROMOTE_USER;
import static com.example.queststore.controllers.web.MentorOptions.TASKS;

public class MentorHandler implements HttpHandler {

    private SessionDAO sessionDAO = new SqliteSessionDAO();
    private UserDAO userDAO = new SqliteUserDAO();
    private TaskDAO taskDAO = new SqliteTaskDAO();

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

            String formData = getFormData(httpExchange);
            System.out.println("Form :" + formData);

            if (formData.contains("logout")) {
                handleLogout(httpExchange);
            } else if (formData.contains("promote")) {
                new PromotionHandler(httpExchange).handleUserPromotion(formData);
            }
        }
    }

    private void handleLogout(HttpExchange httpExchange) throws IOException {
        HttpCookie cookie;
        String sessionCookie = httpExchange.getRequestHeaders().getFirst("Cookie");
        System.out.println("session cookie: " + sessionCookie);
        if (sessionCookie != null) {
            cookie = HttpCookie.parse(sessionCookie).get(0);
            sessionDAO.deleteBySessionId(cookie.getValue());
        }
        redirectToLogin(httpExchange);
    }

    private void redirectToLogin(HttpExchange httpExchange) throws IOException {
        Headers headers = httpExchange.getResponseHeaders();
        String redirect = "/login";
        headers.add("Location", redirect);
        httpExchange.sendResponseHeaders(301, -1);
    }

    private void showMentorPage(HttpExchange httpExchange, HttpCookie cookie) throws IOException {

        String sessionId = cookie.getValue();
        Session session = sessionDAO.getById(sessionId);
        int userId = session.getUserId();
        User user = userDAO.getById(userId);

        handleShowingSubPage(httpExchange, user);
    }

    private void handleShowingSubPage(HttpExchange httpExchange, User user) throws IOException {

        String path = httpExchange.getRequestURI().getPath();
        System.out.println("Path: " + path);
        JtwigTemplate template;
        JtwigModel model;

        String lastSegment = path.substring(path.lastIndexOf('/') + 1);

        switch (lastSegment) {
            case PROMOTE_USER:
                template = JtwigTemplate.classpathTemplate("templates/promote_user.twig");
                model = JtwigModel.newModel();
                model.with("users", userDAO.getAllByRole(UserEntry.BLANK_USER_ROLE));
                sendResponse(httpExchange, template.render(model));
                break;
            case TASKS:
                template = JtwigTemplate.classpathTemplate("templates/display_tasks.twig");
                model = JtwigModel.newModel();
                model.with("tasks", taskDAO.getAll());
                sendResponse(httpExchange, template.render(model));
                break;
            default:
                template = JtwigTemplate.classpathTemplate("templates/mentor_manager.twig");
                model = JtwigModel.newModel();
                model.with("userName", user.getLogin());
                sendResponse(httpExchange, template.render(model));
        }
    }

    private void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void showStaticPage(HttpExchange httpExchange, String pagePath) throws IOException {
        URL fileURL = Resources.getResource(pagePath);
        StaticHandler.sendFile(httpExchange, fileURL);
    }

    private String getFormData(HttpExchange httpExchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), Charsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }
}
