package handlers;

import com.google.common.io.BaseEncoding;
import com.google.common.io.Resources;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.*;
import model.Group;
import model.Mentor;

import model.MentorModel;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;


import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";
        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            URI uri = httpExchange.getRequestURI();

            if (uri.toString().contains("edit")) {
                Integer mentorId = getIdMentorFrom(uri);
                Mentor mentor = getMentor(mentorId);
                response = prepareTemplateEdit(mentor);
            }
            else{
                response = prepareTemplateMain();
            }
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            System.out.println(formData);
            Map<String, String> profileData = parseDataAddMentor(formData);

            Mentor mentor = createMentor(profileData);
            handleUpdateDB(mentor);

            response = prepareTemplateMain();
        }


        renderWebsite(httpExchange, response);

    }

    private String prepareTemplateMain() {
        List<Mentor> mentors = getAllMentors();
        List<Group> groups = getAllGroups();

        JtwigTemplate template = JtwigTemplate.classpathTemplate("admin_manager_template.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("mentors", mentors);
        model.with("groups", groups);

        return template.render(model);
    }

    private String prepareTemplateEdit(Mentor mentor) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("edit_mentor_by_admin.twig");
        JtwigModel model = JtwigModel.newModel();

        return template.render(model);
    }

    private List<Mentor> getAllMentors() {
        AdminDAO adminDAO = new SqliteAdminDAO();
        return adminDAO.getAllMentors();
    }

    private List<Group> getAllGroups() {
        GroupDAO groupDAO = new SqliteGroupDAO();
        return groupDAO.getAllGroups();
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

    private void handleUpdateDB(Mentor mentor) {
        MentorDAO mentorDAO = new SqliteMentorDAO();
        mentorDAO.addMentor(mentor);
    }

    private Mentor createMentor(Map<String, String> mentorData) {
        return new Mentor(
                mentorData.get("name"),
                mentorData.get("last-name"),
                mentorData.get("email")
        );
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

    private Mentor getMentor(Integer mentorId) {
        if (mentorId != null) {
            MentorDAO mentorDAO = new SqliteMentorDAO();
            return mentorDAO.getMentorBy(mentorId);
        }
        throw new IllegalArgumentException("Wrong ID!");
    }
}
