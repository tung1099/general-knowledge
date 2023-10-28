package com.example.common.service.okhttp;

import com.example.common.common.OkHttpCommon;
import com.example.common.utils.BenchMarkUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import java.io.IOException;

@Service
@Slf4j
public class OkHttpService {
    @Autowired
    OkHttpCommon common;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${api.url}")
    String apiUrl;

    public ResponseEntity<JsonNode> getData() {
        StopWatch start = BenchMarkUtils.start();
        try {
            JsonNode result = common.okHttpGET(apiUrl);
            BenchMarkUtils.end(start, "get data");

            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public JsonNode post(Object object) {
        StopWatch start = BenchMarkUtils.start();
        JsonNode responseEntity = null;

        try {
            responseEntity = common.okHttpPOST(apiUrl, object);
            BenchMarkUtils.end(start, "post");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseEntity;
    }

    public ResponseEntity<String> postData(String json, String url){
        try {
            common.okHttpPOSTJsonResponse(json, apiUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String postAsync(Object object) throws Exception {
        StopWatch start = BenchMarkUtils.start();
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("Error during call API: " + e.getMessage());
                BenchMarkUtils.end(start, "API call");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        log.info("API call successful. Response: " + result);
                        BenchMarkUtils.end(start, "API call");
                    } else {
                        log.error("API call failed with response code: " + response.code());
                    }
                } finally {
                    response.close();
                }
            }
        };

        common.okHttpPOSTAsync(apiUrl, object, callback);
        return "OK";
    }
}