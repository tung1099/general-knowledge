package com.example.common.config.avtivemq;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {
    @Value("${activeMq_broker}")
    private String brokerURL;

    @Bean
    public PooledConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerURL);
        PooledConnectionFactory pool = new PooledConnectionFactory();
        pool.setConnectionFactory(connectionFactory);
        pool.setMaxConnections(50); // tối đa 50 connection
        pool.setExpiryTimeout(10000); //10 giây
        pool.setReconnectOnException(true); // tự động kết nối laị khi bị mất kết nối
        return pool;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(connectionFactory());
    }
    @Bean
    public MessageConverter messageConverter() {
        // chuyển đổi dữ liệu thành JSON.
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}

