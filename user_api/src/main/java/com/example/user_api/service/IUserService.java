package com.example.user_api.service;

import com.example.user_api.model.User;
import com.example.user_api.model.UserWithDepartment;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(String id);
    List<User> findUserByAge(int age);
    List<UserWithDepartment> findUsersWithDepartments();
    List<UserWithDepartment> findUsersByNameAndAge(String name, int age);
    List<UserWithDepartment> findUsersInAgeRangeWithField(int minAge, int maxAge);
    User saveActive(User user);
}
