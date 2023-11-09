package com.example.user_api.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "products")
public class Product {
    private int id;
    private String name;
    private String description;
    private int quantity;
    private double price;
}
