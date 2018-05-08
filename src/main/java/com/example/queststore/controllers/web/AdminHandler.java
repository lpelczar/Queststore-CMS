package com.example.queststore.controllers.web;

import com.example.queststore.dao.GroupDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.dao.sqlite.SqliteGroupDAO;
import com.example.queststore.dao.sqlite.SqliteUserDAO;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.data.sessions.Session;
import com.example.queststore.data.sessions.SessionDAO;
import com.example.queststore.data.sessions.SqliteSessionDAO;
import com.example.queststore.models.Group;
import com.example.queststore.models.User;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminHandler implements HttpHandler {

    private SessionDAO sessionDAO = new SqliteSessionDAO();
    private UserDAO userDAO = new SqliteUserDAO();
    private User mentor;
    private User admin;
    private Integer mentorId;


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
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            System.out.println(formData);
            Map<String, String> profileData = parseDataAddMentor(formData);


            if (formData.contains("logout")) {
                handleLogout(httpExchange);

            } else if (checkPostType(formData).contains("Edit")) {
                updateMentorField(profileData);
                handleUpdate(mentor);
                redirectToAdmin(httpExchange);
            }
//            else {
////                handlePromoteUserToMentor(mentor);
////            }

            response = prepareTemplateMain();
        }


        renderWebsite(httpExchange, response);

    }

    private void showAdminPage(HttpExchange httpExchange, HttpCookie cookie) {
        String sessionId = cookie.getValue();
        Session session = sessionDAO.getById(sessionId);
        String response;

        int userId = session.getUserId();
        admin = userDAO.getById(userId);

        URI uri = httpExchange.getRequestURI();

        if (uri.toString().contains("edit")) {
            mentorId = getIdMentorFrom(uri);
            mentor = getMentor(mentorId);
            response = prepareTemplateEdit(mentor);
        }
        else{
            response = prepareTemplateMain();
        }

        renderWebsite(httpExchange, response);
    }

    private String prepareTemplateMain() {
        List<User> mentors = getAllMentors();
        List<Group> groups = getAllGroups();
        List<User> blankUsers = getAllBlankUsers();

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin_manager_template.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("mentors", mentors);
        model.with("groups", groups);
        model.with("blankUsers", blankUsers);

        return template.render(model);
    }

    private String prepareTemplateEdit(User mentor) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/edit_mentor_by_admin.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("login", mentor.getLogin());
        model.with("name", mentor.getName());
        model.with("phoneNumber", mentor.getPhoneNumber());
        model.with("email", mentor.getEmail());


        return template.render(model);
    }

    private List<User> getAllBlankUsers() {
        UserDAO userDAO = new SqliteUserDAO();
        return userDAO.getAllByRole(UserEntry.BLANK_USER_ROLE);
    }

    private List<User> getAllMentors() {
        UserDAO userDAO = new SqliteUserDAO();
        return userDAO.getAllByRole(UserEntry.MENTOR_ROLE);
    }

    private List<Group> getAllGroups() {
        GroupDAO groupDAO = new SqliteGroupDAO();
        return groupDAO.getAll();
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

    private String checkPostType(String formData) {
        String[] form = formData.split("&");
        int TYPE_INDEX = form.length-1;
        return form[TYPE_INDEX];
    }

    private Map<String, String> parseDataAddMentor(String formData) {
        int VALUE_INDEX = 1;
        int FORM_NAME_INDEX = 0;

        Map<String, String> profileValues = new HashMap<>();
        String[] data = formData.split("&");

        for (String pair : data) {
            String[] keyValue = pair.split("=");

            if (keyValue.length > 1) {
                String value = decodeValue(keyValue[VALUE_INDEX]);
                profileValues.put(keyValue[FORM_NAME_INDEX], value);
            }
        }
        return profileValues;
    }

    private String decodeValue(String value) {
        try {
            return new URLDecoder().decode(value, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            System.err.println(e.getClass().getName() + " --> " + e.getMessage());
        }
        return "";
    }

    private void updateMentorField(Map<String, String> userProfile) {
        for (String key : userProfile.keySet()) {
            switch (key) {
                case "login":
                    mentor.setLogin(userProfile.get("login"));
                    break;
                case "name":
                    mentor.setName(userProfile.get("name"));
                    break;
                case "phone-number":
                    mentor.setPhoneNumber(userProfile.get("phone-number"));
                    break;
                case "email":
                    mentor.setEmail(userProfile.get("email"));
                    break;
            }
        }
    }

    private void handleUpdate(User mentor) {
        UserDAO userDAO = new SqliteUserDAO();
        userDAO.update(mentor);
    }

    private Integer getIdMentorFrom(URI uri) {
        String[] values = uri.toString().split("/");

        for (String element : values) {
            if (element.matches("[0-9]+")) {
                return Integer.valueOf(element);
            }
        }
        return null;
    }

    private User getMentor(Integer mentorId) {
        if (mentorId != null) {
            UserDAO userDAO = new SqliteUserDAO();
            return userDAO.getById(mentorId);
        }
        throw new IllegalArgumentException("Wrong ID!");
    }

    private void redirectToAdmin(HttpExchange httpExchange) {
        Headers responseHeaders = httpExchange.getResponseHeaders();

        responseHeaders.add("Location", "/admin");

        try {
            httpExchange.sendResponseHeaders(302, -1);
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

    private void redirectToLogin(HttpExchange httpExchange) throws IOException {
        Headers headers = httpExchange.getResponseHeaders();
        String redirect = "/login";
        headers.add("Location", redirect);
        httpExchange.sendResponseHeaders(301, -1);
    }
}
