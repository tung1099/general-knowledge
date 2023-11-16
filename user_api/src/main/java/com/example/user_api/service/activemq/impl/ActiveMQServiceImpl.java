package com.example.user_api.service.activemq.impl;

import com.example.common.utils.GsonUtil;
import com.example.user_api.service.activemq.IActiveMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActiveMQServiceImpl implements IActiveMQService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(String queueName, Object obj) {
        try {
            String mess = GsonUtil.toJson(obj);
            log.info("Log message: {}", mess);
            jmsTemplate.convertAndSend(queueName, mess);
        } catch (Exception ex) {
            log.error("Error send log to activeMq {}", ex.getStackTrace());
        }
    }
}
