package com.example.queststore.controllers.web;

import com.example.queststore.dao.UserDAO;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

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
            sendRegistrationPage(httpExchange);
        }

        if (method.equals(POST_METHOD)) {
            String formData = getFormData(httpExchange);
            System.out.println(formData);
            // handleRegistration(httpExchange, formData);
        }
    }

    private void sendRegistrationPage(HttpExchange httpExchange) throws IOException {
        URL url = Resources.getResource("static/register.html");
        StaticHandler.sendFile(httpExchange, url);
    }

    private String getFormData(HttpExchange httpExchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), Charsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }
}
