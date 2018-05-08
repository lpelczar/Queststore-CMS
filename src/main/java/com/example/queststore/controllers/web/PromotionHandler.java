package com.example.queststore.controllers.web;

import com.example.queststore.dao.UserDAO;
import com.example.queststore.dao.sqlite.SqliteUserDAO;
import com.example.queststore.data.contracts.UserEntry;
import com.example.queststore.models.User;
import com.google.common.base.Charsets;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

class PromotionHandler {

    private UserDAO userDAO = new SqliteUserDAO();
    private HttpExchange httpExchange;

    PromotionHandler(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }


    void handleUserPromotion(String formData) throws IOException {

        final int USER_ID_INDEX = 0;
        final int ROLE_INDEX = 1;
        List<String> values = parseFormData(formData);

        User user = userDAO.getById(Integer.parseInt(values.get(USER_ID_INDEX)));

        if (values.get(ROLE_INDEX).contains("student")) {
            user.setRole(UserEntry.STUDENT_ROLE);
        } else if (values.get(ROLE_INDEX).contains("mentor")) {
            user.setRole(UserEntry.MENTOR_ROLE);
        }
        userDAO.update(user);
        httpExchange.getResponseHeaders().add("Location", "/mentor/promote");
        httpExchange.sendResponseHeaders(301, -1);
    }

    private List<String> parseFormData(String formData) throws UnsupportedEncodingException {
        final int VALUE_INDEX = 1;
        String[] pairs = formData.split("&");
        List<String> values = new ArrayList<>();
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            values.add(URLDecoder.decode(keyValue[VALUE_INDEX], Charsets.UTF_8.displayName()));
        }
        return values;
    }
}
