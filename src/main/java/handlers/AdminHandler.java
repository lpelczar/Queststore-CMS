package handlers;

import com.google.common.io.BaseEncoding;
import com.google.common.io.Resources;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.*;
import model.Group;
import model.Mentor;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;


import java.io.*;
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
            response = prepareTemplate();
        }

        if (method.equals("POST")) {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            Map<String, String> profileData = parseDataAddMentor(formData);
            Mentor mentor = createMentor(profileData);
            handleUpdateDB(mentor);

            response = prepareTemplate();
        }


        renderWebsite(httpExchange, response);

    }

    private String prepareTemplate() {
        List<Mentor> mentors = getAllMentors();
        List<Group> groups = getAllGroups();

        JtwigTemplate template = JtwigTemplate.classpathTemplate("admin_manager_template.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("mentors", mentors);
        model.with("groups", groups);

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
}
