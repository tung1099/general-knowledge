package com.example.user_api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String departmentId;
}
