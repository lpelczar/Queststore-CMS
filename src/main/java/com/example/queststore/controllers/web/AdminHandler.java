package com.example.queststore.controllers.web;

import com.example.queststore.dao.GroupDAO;
import com.example.queststore.dao.UserDAO;
import com.example.queststore.dao.sqlite.SqliteGroupDAO;
import com.example.queststore.dao.sqlite.SqliteUserDAO;
import com.example.queststore.data.contracts.UserEntry;
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

    private Integer mentorId;
    private SessionDAO sessionDAO = new SqliteSessionDAO();
//
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";
        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
//            URI uri = httpExchange.getRequestURI();
//
//            if (uri.toString().contains("edit")) {
//                mentorId = getIdMentorFrom(uri);
//                Mentor mentor = getMentor(mentorId);
//                response = prepareTemplateEdit(mentor);
//            }
//            else{
//                response = prepareTemplateMain();
//            }
            response = prepareTemplateMain();
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            System.out.println(formData);
//            Map<String, String> profileData = parseDataAddMentor(formData);
//            Mentor mentor = createMentor(profileData);
//
//            if (formData.contains("logout")) {
//                handleLogout(httpExchange);
//
//            } else if (checkPostType(formData).contains("Edit")) {
//                handleUpdate(mentor);
//                redirectToAdmin(httpExchange);
//            }
//            else {
//                handleAddMentorDB(mentor);
//            }

            response = prepareTemplateMain();
        }


        renderWebsite(httpExchange, response);

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

    private String prepareTemplateMain() {
        List<User> mentors = getAllMentors();
        List<Group> groups = getAllGroups();

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/admin_manager_template.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("mentors", mentors);
        model.with("groups", groups);

        return template.render(model);
    }

//    private String prepareTemplateEdit(Mentor mentor) {
//        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/edit_mentor_by_admin.twig");
//        JtwigModel model = JtwigModel.newModel();
//
//        model.with("name", mentor.getName());
//        model.with("lastName", mentor.getLastName());
//        model.with("email", mentor.getEmail());
//
//        return template.render(model);
//    }

    private List<User> getAllMentors() {
        String mentors = UserEntry.MENTOR_ROLE;

        UserDAO userDAO = new SqliteUserDAO();
        return userDAO.getAllByRole(mentors);
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
            String value = decodeValue(keyValue[VALUE_INDEX]);

            profileValues.put(keyValue[FORM_NAME_INDEX], value);
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

//    private void handleUpdate(Mentor mentor) {
//        MentorDAO mentorDAO = new SqliteMentorDAO();
//        mentorDAO.updateMentor(mentor, mentorId);
//    }
//
//    private void handleAddMentorDB(Mentor mentor) {
//        MentorDAO mentorDAO = new SqliteMentorDAO();
//        mentorDAO.addMentor(mentor);
//    }
//
//    private Mentor createMentor(Map<String, String> mentorData) {
//        return new Mentor(
//                mentorData.get("name"),
//                mentorData.get("last-name"),
//                mentorData.get("email")
//        );
//    }

    private Integer getIdMentorFrom(URI uri) {
        String[] values = uri.toString().split("/");
        for (String element : values) {
            if (element.matches("[0-9]+")) {
                return Integer.valueOf(element);
            }
        }
        return null;
    }

//    private User getMentor(Integer mentorId) {
//
//        if (mentorId != null) {
//            UserDAO userDAO = new SqliteUserDAO();
//            return userDAO.getAllByRole(mentors);
//        }
//        throw new IllegalArgumentException("Wrong ID!");
//    }

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
}
