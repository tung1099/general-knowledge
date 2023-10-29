package com.example.user_api.service;

import com.example.common.config.exception.exception_class.ValidateException;
import com.example.common.utils.BenchMarkUtils;
import com.example.user_api.model.User;
import com.example.user_api.model.UserWithDepartment;
import com.example.user_api.repository.user.IUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements IUserService{
    @Autowired
    IUserRepository userRepository;
    @Override
    public List<User> findAll() {
        StopWatch start = BenchMarkUtils.start();
        BenchMarkUtils.end(start, "API: find all User");
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            throw new ValidateException("ID không hợp lệ");
        }
        return userOptional;
    }

    @Override
    public List<User> findUserByAge(int age) {
        return null;
    }

    @Override
    public List<UserWithDepartment> findUsersWithDepartments() {
        return null;
    }

    @Override
    public List<UserWithDepartment> findUsersByNameAndAge(String name, int age) {
        return null;
    }

    @Override
    public List<UserWithDepartment> findUsersInAgeRangeWithField(int minAge, int maxAge) {
        return null;
    }
}
