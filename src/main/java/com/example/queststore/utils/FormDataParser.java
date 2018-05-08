package com.example.queststore.utils;

import com.google.common.base.Charsets;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class FormDataParser {

    public List<String> getKeys(String formData) throws UnsupportedEncodingException {
        final int KEY_INDEX = 0;
        String[] pairs = formData.split("&");
        List<String> values = new ArrayList<>();
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            values.add(URLDecoder.decode(keyValue[KEY_INDEX], Charsets.UTF_8.displayName()));
        }
        return values;
    }

    public List<String> getValues(String formData) throws UnsupportedEncodingException {
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
