package com.example.queststore.controllers.web;

import com.example.queststore.dao.UserDAO;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.User;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

class RegistrationHandler implements HttpHandler {

    private UserDAO userDAO;

    RegistrationHandler(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        final String GET_METHOD = "GET";
        final String POST_METHOD = "POST";

        String method = httpExchange.getRequestMethod();
        if (method.equals(GET_METHOD)) {
            sendPage(httpExchange, "static/register.html");
        }

        if (method.equals(POST_METHOD)) {
            String formData = getFormData(httpExchange);
            handleRegistration(httpExchange, formData);
        }
    }

    private void handleRegistration(HttpExchange httpExchange, String formData) throws IOException {
        System.out.println(formData);

        User user = parseRegistrationData(formData);

        if (userDAO.add(user)) {
            sendSuccessPage(httpExchange, user);
        } else {
            sendPage(httpExchange, "static/registrationerror.html");
        }
    }

    private void sendSuccessPage(HttpExchange httpExchange, User user) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/registration_success.twig");
        JtwigModel model = JtwigModel.newModel();
        model.with("userLogin", user.getLogin());
        model.with("userName", user.getName());
        model.with("userEmail", user.getEmail());
        model.with("userPhoneNumber", user.getPhoneNumber());
        sendResponse(httpExchange, template.render(model));
    }

    private void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private User parseRegistrationData(String formData) throws UnsupportedEncodingException {

        final int LOGIN_INDEX = 0;
        final int PASSWORD_INDEX = 1;
        final int NAME_INDEX = 2;
        final int EMAIL_INDEX = 3;
        final int PHONE_INDEX = 4;
        final int VALUE_INDEX = 1;

        String[] pairs = formData.split("&");
        List<String> values = new ArrayList<>();
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            values.add(URLDecoder.decode(keyValue[VALUE_INDEX], Charsets.UTF_8.displayName()));
        }
        return new User(values.get(NAME_INDEX), values.get(LOGIN_INDEX), values.get(EMAIL_INDEX),
                values.get(PASSWORD_INDEX), values.get(PHONE_INDEX), UserEntry.BLANK_USER_ROLE);
    }

    private void sendPage(HttpExchange httpExchange, String path) throws IOException {
        URL url = Resources.getResource(path);
        StaticHandler.sendFile(httpExchange, url);
    }

    private String getFormData(HttpExchange httpExchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), Charsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }
}
