package handlers;

import com.google.common.io.Resources;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.AdminDAO;
import dao.SqliteAdminDAO;
import model.Mentor;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;


import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

public class AdminHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) {
        String response;
        String method = httpExchange.getRequestMethod();

        response = prepareTemplate();

        renderWebsite(httpExchange, response);

    }

    private String prepareTemplate() {
        List<Mentor> mentors = getAllMentors();

        JtwigTemplate template = JtwigTemplate.classpathTemplate("admin_manager_template.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("mentors", mentors);

        return template.render(model);
    }

    private List<Mentor> getAllMentors() {
        AdminDAO adminDAO = new SqliteAdminDAO();
        return adminDAO.getAllMentors();
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
}
