package com.example.queststore.controllers.web;

import com.example.queststore.dao.UserDAO;
import com.example.queststore.dao.sqlite.SqliteUserDAO;
import com.example.queststore.data.sessions.Session;
import com.example.queststore.data.sessions.SessionDAO;
import com.example.queststore.data.sessions.SqliteSessionDAO;
import com.example.queststore.models.Group;
import com.example.queststore.models.User;
import com.example.queststore.utils.WebDataTools;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class AdminHandler extends WebDataTools implements HttpHandler {

    private ProfileEditorHandler profileEditorHandler = new ProfileEditorHandler();
    private SessionDAO sessionDAO = new SqliteSessionDAO();
    private UserDAO userDAO = new SqliteUserDAO();
    private User admin;


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        HttpCookie cookie;
        String response = "";
        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            String sessionCookie = httpExchange.getRequestHeaders().getFirst("Cookie");

            if (sessionCookie != null) {
                cookie = HttpCookie.parse(sessionCookie).get(0);

                if (sessionDAO.getById(cookie.getValue()) != null) {
                    showAdminPage(httpExchange, cookie);
                    return;
                }
            }

            redirectToLogin(httpExchange);
        }

        if (method.equals("POST")) {
            String formData = getSubmittedWebData(httpExchange);
            Map<String, String> profileData = parseDataAddMentor(formData);
            System.out.println(formData);


            if (formData.contains("logout")) {
                handleLogout(httpExchange);

            } else if (formData.contains("Accept")) {
                int userId = profileEditorHandler.getUserIdFrom(profileData);
                User user = profileEditorHandler.findUserBy(userId);

                profileEditorHandler.update(profileData, user);
                profileEditorHandler.updateDb(user);

                redirectToAdmin(httpExchange);
            }
            else if (formData.contains("mentor profile")) {
//                int mentorId =
//                redirectToMentorProfile(httpExchange, mentorId);
            }

            response = prepareTemplateMain();
        }


        renderWebsite(httpExchange, response);

    }

    private void showAdminPage(HttpExchange httpExchange, HttpCookie cookie) {
        String sessionId = cookie.getValue();
        Session session = sessionDAO.getById(sessionId);
        String response;

        int adminId = session.getUserId();
        admin = userDAO.getById(adminId);

        URI uri = httpExchange.getRequestURI();

        if (uri.toString().contains("edit")) {
            Integer userId = getUserIdFrom(uri);
            User user = profileEditorHandler.findUserBy(userId);

            response = prepareTemplateEdit(user);
        }
        else{
            response = prepareTemplateMain();
        }

        renderWebsite(httpExchange, response);
    }

    private String prepareTemplateMain() {
        List<User> mentors = profileEditorHandler.getAllMentors();
        List<Group> groups = profileEditorHandler.getAllGroups();
        List<User> blankUsers = profileEditorHandler.getAllBlankUsers();

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin_manager_template.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("mentors", mentors);
        model.with("groups", groups);
        model.with("blankUsers", blankUsers);
        model.with("admin_id", admin.getId());

        return template.render(model);
    }

    private String prepareTemplateEdit(User user) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/edit_mentor_by_admin.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("login", user.getLogin());
        model.with("password", user.getPassword());
        model.with("name", user.getName());
        model.with("phoneNumber", user.getPhoneNumber());
        model.with("email", user.getEmail());
        model.with("user_id", user.getId());


        return template.render(model);
    }

    private void renderWebsite(HttpExchange httpExchange, String response) {
        try {
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }
        catch (IOException e) {
            System.err.println(e.getClass().getName() + " --> " + e.getMessage());
        }
    }

    private void handleLogout(HttpExchange httpExchange) throws IOException {
        HttpCookie cookie;
        String sessionCookie = httpExchange.getRequestHeaders().getFirst("Cookie");
        if (sessionCookie != null) {
            cookie = HttpCookie.parse(sessionCookie).get(0);
            sessionDAO.deleteBySessionId(cookie.getValue());
        }
        redirectToLogin(httpExchange);
    }

    private void redirectToAdmin(HttpExchange httpExchange) throws IOException {
        Headers responseHeaders = httpExchange.getResponseHeaders();
        String redirect = "/admin";
        responseHeaders.add("Location", redirect);
        httpExchange.sendResponseHeaders(302, -1);
    }

    private void redirectToLogin(HttpExchange httpExchange) throws IOException {
        Headers headers = httpExchange.getResponseHeaders();
        String redirect = "/login";
        headers.add("Location", redirect);
        httpExchange.sendResponseHeaders(301, -1);
    }

    private void redirectToMentorProfile(HttpExchange httpExchange, Integer mentorId) throws IOException {
        Headers headers = httpExchange.getResponseHeaders();
        String redirect = "/mentor-profile/" + mentorId;
        headers.add("Location", redirect);
        httpExchange.sendResponseHeaders(301, -1);
    }
}
