package com.example.user_api.activemq;

import com.example.user_api.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@EnableJms
@Slf4j
public class UserListener {
    @JmsListener(destination = "user_creation_queue")
    public void receiveUserCreationMessage(User user) {
        log.info("Received and processed User creation message: {}", user.getName());
    }
    @JmsListener(destination = "user_find_all_queue")
    public void receiveUserFindAllMessage() {
        log.info("Received and processed find all User message");
    }
}
