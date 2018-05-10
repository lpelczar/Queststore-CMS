package com.example.queststore.controllers.web;

import com.example.queststore.dao.UserDAO;
import com.example.queststore.data.sessions.Session;
import com.example.queststore.data.sessions.SessionDAO;
import com.example.queststore.models.User;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.Resources;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class LoginHandler implements HttpHandler {

    private int sessionCounter = 0;
    private UserDAO userDAO;
    private SessionDAO sessionDAO;

    LoginHandler(UserDAO userDAO, SessionDAO sessionDAO) {
        this.userDAO = userDAO;
        this.sessionDAO = sessionDAO;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        final String GET_METHOD = "GET";
        final String POST_METHOD = "POST";
        String method = httpExchange.getRequestMethod();
        HttpCookie cookie;

        if (method.equals(GET_METHOD)) {
            String sessionCookie = httpExchange.getRequestHeaders().getFirst("Cookie");
            if (sessionCookie != null) {
                cookie = HttpCookie.parse(sessionCookie).get(0);
                if (sessionDAO.getById(cookie.getValue()) != null) {
                    makeRedirection(httpExchange, cookie);
                    return;
                }
            }
            sendLoginPage(httpExchange);
        }

        if (method.equals(POST_METHOD)) {
            String formData = getFormData(httpExchange);
            handleLoggingIn(httpExchange, formData);
        }
    }

    private String getFormData(HttpExchange httpExchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), Charsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    private void handleLoggingIn(HttpExchange httpExchange, String formData) throws IOException {

        HttpCookie cookie;
        Pair<String, String> loginPassword = parseLoginData(formData);

        if (userDAO.getByLoginAndPassword(loginPassword.getKey(), loginPassword.getValue()) != null) {

            sessionCounter++;
            String sessionId = Hashing.sha256().hashString(loginPassword.getKey() +
                    Integer.toString(sessionCounter), Charsets.UTF_8).toString();
            User user = userDAO.getByLoginAndPassword(loginPassword.getKey(), loginPassword.getValue());
            cookie = new HttpCookie("sessionId", sessionId);
            httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());


            sessionDAO.add(new Session(sessionId, user.getId()));
            makeRedirection(httpExchange, cookie);

        } else {
            sendLoggingErrorPage(httpExchange);
        }
    }

    private void makeRedirection(HttpExchange httpExchange, HttpCookie cookie) throws IOException {

        String sessionId = cookie.getValue();
        Session session = sessionDAO.getById(sessionId);
        int userId = session.getUserId();
        User user = userDAO.getById(userId);

        if (user.getRole().equals("Admin")) {
            Headers headers = httpExchange.getResponseHeaders();
            String redirect = "/admin";
            headers.add("Location", redirect);
            headers.add("Set-Cookie", cookie.toString());
            httpExchange.sendResponseHeaders(301, -1);
        }

        if (user.getRole().equals("Mentor")) {
            Headers headers = httpExchange.getResponseHeaders();
            String redirect = "/mentor/" + userId + "/students";
            headers.add("Location", redirect);
            headers.add("Set-Cookie", cookie.toString());
            httpExchange.sendResponseHeaders(301, -1);
        }
    }

    private void sendLoginPage(HttpExchange httpExchange) throws IOException {
        URL url = Resources.getResource("static/index.html");
        StaticHandler.sendFile(httpExchange, url);
    }

    private void sendLoggingErrorPage(HttpExchange httpExchange) throws IOException {
        URL url = Resources.getResource("static/error.html");
        StaticHandler.sendFile(httpExchange, url);
    }

    private Pair<String, String> parseLoginData(String formData) throws UnsupportedEncodingException {

        final int LOGIN_INDEX = 0;
        final int PASSWORD_INDEX = 1;
        final int VALUE_INDEX = 1;

        String[] pairs = formData.split("&");
        List<String> values = new ArrayList<>();
        for(String pair : pairs){
            String[] keyValue = pair.split("=");
            values.add(URLDecoder.decode(keyValue[VALUE_INDEX], Charsets.UTF_8.displayName()));
        }
        return new Pair<>(values.get(LOGIN_INDEX), values.get(PASSWORD_INDEX));
    }
}
