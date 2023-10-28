package com.example.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.json.XML;

public class GsonUtil {
    public static <T> T parse(String json, Class<T> classOfT) {
        Gson gson = GsonProvider.getGson();
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object object) {
        Gson gson = GsonProvider.getGson();
        return gson.toJson(object);
    }

    public static String toPrettyJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }

    public static <T> T convertXmlToObject(String xml, Class<T> classOfT) {
        if (StringUtils.checkEmpty(xml)) return null;
        JSONObject json = XML.toJSONObject(xml);
        String jsonString = json.toString(4);
        return parse(jsonString, classOfT);
    }
}

