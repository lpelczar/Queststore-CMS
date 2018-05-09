package com.example.queststore.utils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class WebDataTools {


    public Map<String, String> parseDataAddMentor(String formData) {
        int VALUE_INDEX = 1;
        int FORM_NAME_INDEX = 0;

        Map<String, String> profileValues = new HashMap<>();
        String[] data = formData.split("&");

        for (String pair : data) {
            String[] keyValue = pair.split("=");

            if (keyValue.length > 1) {
                String value = decodeValue(keyValue[VALUE_INDEX]);
                profileValues.put(keyValue[FORM_NAME_INDEX], value);
            }
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

    public Integer getUserIdFrom(URI uri) {
        String[] values = uri.toString().split("/");

        for (String element : values) {
            if (element.matches("[0-9]+")) {
                return Integer.valueOf(element);
            }
        }
        return null;
    }
}
