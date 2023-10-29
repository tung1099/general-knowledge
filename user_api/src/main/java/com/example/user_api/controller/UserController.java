package com.example.user_api.controller;

import com.example.common.config.avtivemq.JmsConfig;
import com.example.user_api.model.User;
import com.example.user_api.repository.user.UserRepositoryImpl;
import com.example.user_api.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserRepositoryImpl userRepository;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    JmsTemplate jmsTemplate;

    @GetMapping
    public ResponseEntity<?> findAll(){
        log.info("\n--------------------------------------------------------------------------->\n");
        log.info("[GET] ['{}']", ServletUriComponentsBuilder.fromCurrentRequest().build());
        List<User> users = userRepository.findAll();
        jmsTemplate.convertAndSend("user_find_all_queue", "Find all users");
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        log.info("\n--------------------------------------------------------------------------->\n");
        log.info("[GET] ['{}']", ServletUriComponentsBuilder.fromCurrentRequest().build());
        Optional<User> users = userService.findById(id);
        return ResponseEntity.ok(users);
    }
    @GetMapping("/with-department")
    public ResponseEntity<?> findUsersWithDepartments(){
        log.info("\n--------------------------------------------------------------------------->\n");
        log.info("[GET] ['{}']", ServletUriComponentsBuilder.fromCurrentRequest().build());
        return ResponseEntity.ok(userRepository.findUsersWithDepartments());
    }
}
