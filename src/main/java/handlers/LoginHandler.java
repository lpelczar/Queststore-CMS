package handlers;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.Resources;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.LoginDAO;
import data.sessiondatabase.Session;
import data.sessiondatabase.SessionDAO;
import javafx.util.Pair;
import model.database.User;

import java.io.*;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class LoginHandler implements HttpHandler {

    private int sessionCounter = 0;
    private LoginDAO loginDAO;
    private SessionDAO sessionDAO;

    LoginHandler(LoginDAO loginDAO, SessionDAO sessionDAO) {
        this.loginDAO = loginDAO;
        this.sessionDAO = sessionDAO;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        final String GET_METHOD = "GET";
        final String POST_METHOD = "POST";
        String method = httpExchange.getRequestMethod();
        System.out.println(method);
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
            System.out.println(formData);
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
        System.out.println(loginPassword.getKey() + " " + loginPassword.getValue());

        if (loginDAO.getByLoginAndPassword(loginPassword.getKey(), loginPassword.getValue()) != null) {

            sessionCounter++;
            String sessionId = Hashing.sha256().hashString(loginPassword.getKey() +
                    Integer.toString(sessionCounter), Charsets.UTF_8).toString();
            User user = loginDAO.getByLoginAndPassword(loginPassword.getKey(), loginPassword.getValue());
            cookie = new HttpCookie("sessionId", sessionId);
            httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());

            System.out.println("Session id: " + sessionId + " User id: " + user.getUserId());

            sessionDAO.add(new Session(sessionId, user.getUserId()));
            makeRedirection(httpExchange, cookie);

        } else {
            sendLoggingErrorPage(httpExchange);
        }
    }

    private void makeRedirection(HttpExchange httpExchange, HttpCookie cookie) throws IOException {

        String sessionId = cookie.getValue();
        Session session = sessionDAO.getById(sessionId);
        int userId = session.getUserId();
        User user = loginDAO.getById(userId);

        if (user.getRoleId() == 1) {
            Headers headers = httpExchange.getResponseHeaders();
            String redirect = "/admin";
            headers.add("Location", redirect);
            headers.add("Set-Cookie", cookie.toString());
            httpExchange.sendResponseHeaders(301, -1);
        }

        if (user.getRoleId() == 2) {
            System.out.println("Mentor logged!");
            Headers headers = httpExchange.getResponseHeaders();
            String redirect = "/mentor/" + userId;
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
