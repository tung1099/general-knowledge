package com.example.user_api.repository.user;

import com.example.user_api.model.User;
import com.example.user_api.model.UserWithDepartment;

import java.util.List;

public interface IUserRepository {
    List<User> findAll();
    User findById(String id);
    List<User> findUserByAge(int age);
    List<UserWithDepartment> findUsersWithDepartments();
    List<UserWithDepartment> findUsersByNameAndAge(String name, int age);
    List<UserWithDepartment> findUsersInAgeRangeWithField(int minAge, int maxAge);
}
