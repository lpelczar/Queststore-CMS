package handlers;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.Resources;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.LoginDAO;
import javafx.util.Pair;
import model.database.User;

import java.io.*;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginHandler implements HttpHandler {

    private Map<String, Integer> sessionsUsers = new HashMap<>();
    private int sessionCounter = 0;
    private LoginDAO loginDAO;

    LoginHandler(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
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
                if (sessionsUsers.containsKey(cookie.getValue())) {
                    makeRedirection(httpExchange, cookie.getValue());
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
        System.out.println(loginPassword);

        if (loginDAO.getByLoginAndPassword(loginPassword.getKey(), loginPassword.getValue()) != null) {

            sessionCounter++;
            String sessionId = Hashing.sha256().hashString(loginPassword.getKey() +
                    Integer.toString(sessionCounter), Charsets.UTF_8).toString();
            User user = loginDAO.getByLoginAndPassword(loginPassword.getKey(), loginPassword.getValue());
            cookie = new HttpCookie("sessionId", sessionId);
            httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
            sessionsUsers.put(sessionId, user.getUserId());
            System.out.println(sessionsUsers.toString());
            makeRedirection(httpExchange, sessionId);

        } else {
            sendLoggingErrorPage(httpExchange);
        }
    }

    private void makeRedirection(HttpExchange httpExchange, String sessionId) throws IOException {

//        int userId = sessionsUsers.get(sessionId);
//        User user = userDAO.getById(userId);
//        model.with("userLogin", user.getLogin());
//        String response = template.render(model);
//        httpExchange.sendResponseHeaders(200, response.length());
//        OutputStream os = httpExchange.getResponseBody();
//        os.write(response.getBytes());
//        os.close();
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
        return new Pair<>(values.get(LOGIN_INDEX), makeSHA256Hash(values.get(PASSWORD_INDEX)));
    }

    private String makeSHA256Hash(String input) {
        return Hashing.sha256().hashString(input, Charsets.UTF_8).toString();
    }
}
