package com.example.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class GsonProvider {


    private static Gson gson = new Gson();
    private static JsonParser jsonParser = new JsonParser();

    public static Gson getGson() {
        return gson;
    }

}
