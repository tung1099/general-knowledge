package com.example.user_api.save_es;

import com.google.gson.Gson;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Component
public class ActiveMQConsumer {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @JmsListener(destination = "dialog_tv360")
    public void receiveMessage(String message) {
        // Chuyển message từ JSON sang đối tượng DialogData
        DialogData dialogData = convertJsonToDialogData(message);
//Phương thức này gọi convertJsonToDialogData để chuyển đổi chuỗi JSON từ tham số message thành một đối tượng DialogData.
// Trong trường hợp này, dữ liệu JSON được chuyển đổi thành một đối tượng DialogData để sau đó lưu vào Elasticsearch.

        // Lưu dialogData vào Elasticsearch
        elasticsearchRestTemplate.save(dialogData);
    }

    private DialogData convertJsonToDialogData(String json) {
        // Thực hiện chuyển đổi từ JSON sang đối tượng DialogData
        Gson gson = new Gson();
        DialogData dialogData = gson.fromJson(json, DialogData.class);
        return dialogData;
    }
}

