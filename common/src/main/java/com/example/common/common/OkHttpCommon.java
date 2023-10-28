package com.example.common.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class OkHttpCommon {
    @Autowired
    OkHttpClient client;

    public static final String JSON = "application/json; charset=utf-8";
    public static String CONNECTION_TIME_OUT = "CONNECTION TIME OUT";
    public JsonNode okHttpGET(String url) throws Exception {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String result = response.body().string();
                log.info("Result " + result);

                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readTree(result);
            } else {
                log.error("Request failed with code: " + response.code());
            }
        } catch (java.net.SocketTimeoutException e){
            log.error("Time out when getting URL " + url);
        } catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    public JsonNode okHttpPOST(String url, Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(object);
        RequestBody requestBody = RequestBody.create(MediaType.parse(JSON), jsonData);

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                String result = response.body().string();
                log.info("Result " + result);
                JsonNode jsonResult = objectMapper.readTree(result);
                return jsonResult;
            } else {
                log.error("Request failed with code: " + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public String okHttpPOSTJsonResponse(String json, String url) throws Exception {
        String result = "{}";
        try{
            RequestBody body = RequestBody.create(MediaType.parse(JSON), json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            result = response.body().string();
        }catch (java.net.SocketTimeoutException e){
            log.error("Time out when post json " + json + " to " + url);
            result = CONNECTION_TIME_OUT;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
//    public String postData(String url, Map<String> headers, Map<String> parameters) throws IOException {
//        if (headers == null) {
//            headers = new HashMap<>();
//        }
//        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//        StringBuilder sb = new StringBuilder();
//        if (parameters != null) {
//            for (String key : parameters.keySet()) {
//                sb.append('&');
//                sb.append(key);
//                sb.append("=");
//                sb.append(parameters.get(key));
//            }
//        }
//
//        log.info("Requesting to {}: {}", url, sb.substring(1));
//        RequestBody body = RequestBody.create(mediaType, sb.substring(1));
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .headers(Headers.of(headers))
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            String ans = response.body().string();
//            log.error("Response: {}", ans);
//            return ans;
//        } catch (IOException e) {
//            log.error("Exception: {}, Try again", e.getMessage());
//            Response response = client.newCall(request).execute();
//            return response.body().string();
//
//        }
//    }


    public String postFormData(String url, String userName, String accessKey) {
        Request request;
        Response response;
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("username", userName)
                    .add("accessKey", accessKey)
                    .build();
            request = new Request.Builder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .url(url)
                    .post(requestBody)
                    .build();

            response = client.newCall(request).execute();
            String ans = response.body().string();
            log.info(ans);
            return ans;
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public void okHttpPOSTAsync(String url, Object object, Callback callback) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(object);
        RequestBody requestBody = RequestBody.create(MediaType.parse(JSON), jsonData);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(callback);
    }
}