package com.example.user_api.service.activemq;

public interface IActiveMQService {
    void sendMessage(String queueName, Object obj);
}
