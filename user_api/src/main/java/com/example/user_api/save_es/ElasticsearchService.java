package com.example.user_api.save_es;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ElasticsearchService {

    private final RestHighLevelClient elasticsearchClient;

    @Autowired
    public ElasticsearchService(RestHighLevelClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public void saveToElasticsearch(DialogData dialogData) {
        try {
            // Chuyển đổi đối tượng DialogData thành chuỗi JSON
            String jsonData = convertObjectToJson(dialogData);

            // Tạo một yêu cầu index cho Elasticsearch
            IndexRequest request = new IndexRequest("dialog_tv360_index")
                    .source(jsonData, XContentType.JSON.JSON);

            // Thực hiện yêu cầu index và nhận phản hồi
            IndexResponse response = elasticsearchClient.index(request, RequestOptions.DEFAULT);

            // Kiểm tra phản hồi (có thể xử lý thông tin phản hồi nếu cần)
            String documentId = response.getId();
            String index = response.getIndex();
            long version = response.getVersion();
            System.out.println("Dữ liệu đã được lưu vào Elasticsearch. ID tài liệu: " + documentId);
        } catch (IOException e) {
            // Xử lý lỗi khi tương tác với Elasticsearch
            e.printStackTrace();
        }
    }

    private String convertObjectToJson(DialogData dialogData) {
        // Chuyển đổi đối tượng DialogData thành chuỗi JSON bằng thư viện Jackson hoặc Gson
        // Ví dụ sử dụng thư viện Jackson:
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(dialogData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

