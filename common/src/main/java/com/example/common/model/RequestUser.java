package com.example.common.model;

import lombok.Data;

@Data
public class RequestUser {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String departmentId;
}
