package com.example.queststore.controllers.web;

import com.example.queststore.dao.*;
import com.example.queststore.dao.sqlite.*;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.data.sessions.Session;
import com.example.queststore.data.sessions.SessionDAO;
import com.example.queststore.data.sessions.SqliteSessionDAO;
import com.example.queststore.models.User;
import com.example.queststore.utils.FormDataParser;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
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
import java.util.List;

import static com.example.queststore.controllers.web.MentorOptions.*;

public class MentorHandler implements HttpHandler {

    private SessionDAO sessionDAO = new SqliteSessionDAO();
    private UserDAO userDAO = new SqliteUserDAO();
    private TaskDAO taskDAO = new SqliteTaskDAO();
    private ItemDAO itemDAO = new SqliteItemDAO();
    private GroupDAO groupDAO = new SqliteGroupDAO();
    private int mentorId;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();
        HttpCookie cookie;

        if (method.equals("GET")) {
            String sessionCookie = httpExchange.getRequestHeaders().getFirst("Cookie");
            if (sessionCookie != null) {
                cookie = HttpCookie.parse(sessionCookie).get(0);
                Session session = sessionDAO.getById(cookie.getValue());
                if (session != null && isMentor(session.getUserId())) {
                    showMentorPage(httpExchange, cookie);
                    return;
                }
            }
            redirectToPath(httpExchange, "/login");
        }

        if (method.equals("POST")) {

            String formData = getFormData(httpExchange);
            System.out.println("Form :" + formData);
            List<String> keys = new FormDataParser().getKeys(formData);
            String lastKey = keys.get(keys.size() - 1);
            if (lastKey.contains("logout")) {
                handleLogout(httpExchange);
            } else if (lastKey.contains("redirect")) {
                handleRedirection(httpExchange, formData);
            } else if (lastKey.contains("promote")) {
                new PromotionHandler(httpExchange).handleUserPromotion(formData);
            } else if (lastKey.contains("task")) {
                new TaskHandler(httpExchange, mentorId).handle(formData);
            } else if (lastKey.contains("item")) {
                new ItemHandler(httpExchange, mentorId).handle(formData);
            } else if (lastKey.contains("student")) {
                new StudentHandler(httpExchange, mentorId).handle(formData);
            }
        }
    }

    private boolean isMentor(int userId) {
        User user = userDAO.getById(userId);
        return user.getRole().equals(UserEntry.MENTOR_ROLE);
    }

    private void handleRedirection(HttpExchange httpExchange, String formData) throws IOException {

        if (formData.contains("redirect-promote-user")) {
            redirectToPath(httpExchange, "/mentor/" + mentorId + "/promote-user");
        } else if (formData.contains("redirect-tasks")) {
            redirectToPath(httpExchange, "/mentor/" + mentorId + "/tasks");
        } else if (formData.contains("redirect-add-task")) {
            redirectToPath(httpExchange, "/mentor/" + mentorId + "/add-task");
        } else if (formData.contains("redirect-items")) {
            redirectToPath(httpExchange, "/mentor/" + mentorId + "/items");
        } else if (formData.contains("redirect-add-item")) {
            redirectToPath(httpExchange, "/mentor/" + mentorId + "/add-item");
        } else if (formData.contains("redirect-add-student-to-group")) {
            redirectToPath(httpExchange, "/mentor/" + mentorId + "/add-student-to-group");
        } else if (formData.contains("redirect-students")) {
            redirectToPath(httpExchange, "/mentor/" + mentorId + "/students" );
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
        redirectToPath(httpExchange, "/login");
    }

    private void showMentorPage(HttpExchange httpExchange, HttpCookie cookie) throws IOException {
        String sessionId = cookie.getValue();
        Session session = sessionDAO.getById(sessionId);
        int userId = session.getUserId();
        this.mentorId = userId;
        User user = userDAO.getById(userId);
        handleShowingSubPage(httpExchange, user);
    }

    private void handleShowingSubPage(HttpExchange httpExchange, User user) throws IOException {

        String path = httpExchange.getRequestURI().getPath();
        System.out.println("Path: " + path);
        JtwigTemplate template;
        JtwigModel model;

        String[] segments = path.split("/");
        String lastSegment = segments[segments.length - 1].isEmpty() ?
                segments[segments.length - 2] : segments[segments.length - 1];

        switch (lastSegment) {
            case PROMOTE_USER:
                template = JtwigTemplate.classpathTemplate("templates/promote_user.twig");
                model = JtwigModel.newModel();
                model.with("users", userDAO.getAllByRole(UserEntry.BLANK_USER_ROLE));
                sendResponse(httpExchange, template.render(model));
                break;
            case EDIT_TASK:
                String taskId = segments[segments.length - 2];
                new TaskHandler(httpExchange, mentorId).showEditPage(taskId);
                break;
            case TASKS:
                template = JtwigTemplate.classpathTemplate("templates/display_tasks.twig");
                model = JtwigModel.newModel();
                model.with("tasks", taskDAO.getAll());
                sendResponse(httpExchange, template.render(model));
                break;
            case ADD_TASK:
                sendStaticPage(httpExchange, "static/mentor/add_task.html");
                break;
            case EDIT_ITEM:
                String itemId = segments[segments.length - 2];
                new ItemHandler(httpExchange, mentorId).showEditPage(itemId);
                break;
            case ITEMS:
                template = JtwigTemplate.classpathTemplate("templates/display_items.twig");
                model = JtwigModel.newModel();
                model.with("items", itemDAO.getAllItems());
                sendResponse(httpExchange, template.render(model));
                break;
            case ADD_ITEM:
                sendStaticPage(httpExchange, "static/mentor/add_item.html");
                break;
            case ADD_STUDENT_TO_GROUP:
                template = JtwigTemplate.classpathTemplate("templates/add_student_to_group.twig");
                model = JtwigModel.newModel();
                model.with("students", userDAO.getAllByRole(UserEntry.STUDENT_ROLE));
                model.with("groups", groupDAO.getAll());
                sendResponse(httpExchange, template.render(model));
                break;
            case STUDENTS:
                new StudentHandler(httpExchange, mentorId).showStudentsPage();
        }
    }

    private void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private void sendStaticPage(HttpExchange httpExchange, String pagePath) throws IOException {
        URL fileURL = Resources.getResource(pagePath);
        StaticHandler.sendFile(httpExchange, fileURL);
    }

    private String getFormData(HttpExchange httpExchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), Charsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    private void redirectToPath(HttpExchange httpExchange, String path) throws IOException {
        httpExchange.getResponseHeaders().add("Location", path);
        httpExchange.sendResponseHeaders(301, -1);
    }
}
