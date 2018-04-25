package com.example.queststore.handlers;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.LoginDAO;
import dao.QuestDAO;
import dao.SqliteLoginDAO;
import dao.SqliteQuestDAO;
import com.example.queststore.data.sessiondatabase.Session;
import com.example.queststore.data.sessiondatabase.SessionDAO;
import com.example.queststore.data.sessiondatabase.SqliteSessionDAO;
import model.database.Quest;
import model.database.User;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static com.example.queststore.handlers.MentorOptions.*;

public class MentorHandler implements HttpHandler {

    private SessionDAO sessionDAO = new SqliteSessionDAO();
    private LoginDAO loginDAO = new SqliteLoginDAO();
    private QuestDAO questDAO = new SqliteQuestDAO();

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
            System.out.println("Form com.example.queststore.data: " + formData);

            if (formData.contains("logout")) {
                handleLogout(httpExchange);
            } else if (formData.contains("add-quest")) {
                handleAddingQuest(httpExchange, formData);
            } else if (formData.contains("delete-quest")) {
                handleDeletingQuest(httpExchange, formData);
            }
        }
    }

    private void handleDeletingQuest(HttpExchange httpExchange, String formData) throws IOException {

        final int ID_INDEX = 0;
        List<String> values = parseFormData(formData);
        int questId = Integer.parseInt(values.get(ID_INDEX));

        if (questDAO.getById(questId) != null) {
            Quest quest = questDAO.getById(questId);
            if (questDAO.delete(quest)) {
                System.out.println("Success deleting quest!");
            }
        }

        httpExchange.getResponseHeaders().add("Location", "/mentor/showquests");
        httpExchange.sendResponseHeaders(301, -1);
    }

    private void handleAddingQuest(HttpExchange httpExchange, String formData) throws IOException {

        final int NAME_INDEX = 0;
        final int DESCRIPTION_INDEX = 1;
        final int PRICE_INDEX = 2;

        List<String> values = parseFormData(formData);
        Quest quest = new Quest(values.get(NAME_INDEX), values.get(DESCRIPTION_INDEX),
                Integer.parseInt(values.get(PRICE_INDEX)));
        if (questDAO.add(quest)) {
            System.out.println("Success adding quest!");
        }

        httpExchange.getResponseHeaders().add("Location", "/mentor/showquests");
        httpExchange.sendResponseHeaders(301, -1);
    }

    private List<String> parseFormData(String formData) throws UnsupportedEncodingException {
        int VALUE_INDEX = 1;
        String[] pairs = formData.split("&");
        List<String> values = new ArrayList<>();
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            values.add(URLDecoder.decode(keyValue[VALUE_INDEX], Charsets.UTF_8.displayName()));
        }
        return values;
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
        User user = loginDAO.getById(userId);

        handleShowingSubPage(httpExchange, user);
    }

    private void handleShowingSubPage(HttpExchange httpExchange, User user) throws IOException {

        String path = httpExchange.getRequestURI().getPath();
        System.out.println("Path: " + path);
        JtwigTemplate template;
        JtwigModel model;

        String lastSegment = path.substring(path.lastIndexOf('/') + 1);

        switch (lastSegment) {
            case ADD_STUDENT:
                showStaticPage(httpExchange, "static/mentor/create_student.html");
                break;
            case EDIT_STUDENT:
                showStaticPage(httpExchange, "static/mentor/edit_student.html");
                break;
            case ADD_QUEST:
                showStaticPage(httpExchange, "static/mentor/add_quest.html");
                break;
            case SHOW_QUESTS:
                template = JtwigTemplate.classpathTemplate("templates/display_quests.twig");
                model = JtwigModel.newModel();
                model.with("quests", questDAO.getAll());
                sendResponse(httpExchange, template.render(model));
                break;
            case EDIT_QUEST:
                showStaticPage(httpExchange, "static/mentor/edit_quest.html");
                break;
            case DELETE_QUEST:
                template = JtwigTemplate.classpathTemplate("templates/delete_quest.twig");
                model = JtwigModel.newModel();
                model.with("quests", questDAO.getAll());
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
