package com.example.queststore.controllers.web;

import com.example.queststore.dao.ItemDAO;
import com.example.queststore.dao.TaskDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.dao.sqlite.SqliteItemDAO;
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

import static com.example.queststore.controllers.web.MentorOptions.*;

public class MentorHandler implements HttpHandler {

    private SessionDAO sessionDAO = new SqliteSessionDAO();
    private UserDAO userDAO = new SqliteUserDAO();
    private TaskDAO taskDAO = new SqliteTaskDAO();
    private ItemDAO itemDAO = new SqliteItemDAO();

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
            } else if (formData.contains("redirect")) {
                handleRedirection(httpExchange, formData);
            } else if (formData.contains("promote")) {
                new PromotionHandler(httpExchange).handleUserPromotion(formData);
            } else if (formData.contains("Delete+task")) {
                new TaskHandler(httpExchange).handleDeletingTask(formData);
            } else if (formData.contains("add-task")) {
                new TaskHandler(httpExchange).handleAddingTask(formData);
            } else if (formData.contains("edit-task-button")) {
                new TaskHandler(httpExchange).handleEditingTask(formData);
            } else if (formData.contains("Edit+task")) {
                new TaskHandler(httpExchange).handleShowingEditPage(formData);
            }
        }
    }

    private void handleRedirection(HttpExchange httpExchange, String formData) throws IOException {

        if (formData.contains("redirect-promote-user")) {
            redirectToPath(httpExchange, "/mentor/promote-user");
        } else if (formData.contains("redirect-tasks")) {
            redirectToPath(httpExchange, "/mentor/tasks");
        } else if (formData.contains("redirect-add-task")) {
            redirectToPath(httpExchange, "/mentor/add-task");
        } else if (formData.contains("redirect-items")) {
            redirectToPath(httpExchange, "/mentor/items");
        } else if (formData.contains("redirect-add-item")) {
            redirectToPath(httpExchange, "/mentor/add-item");
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
            case EDIT_TASK:
                String[] segments = path.split("/");
                String taskId = segments[segments.length - 2];
                new TaskHandler(httpExchange).showEditPage(taskId);
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
            case ITEMS:
                template = JtwigTemplate.classpathTemplate("templates/display_items.twig");
                model = JtwigModel.newModel();
                model.with("items", itemDAO.getAllItems());
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
