package com.example.common.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

    @Bean("asyncTask")
    public ThreadPoolTaskExecutor asyncTask() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // Số lượng luồng cố định
        executor.setMaxPoolSize(50); // Số lượng luồng tối đa
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("asyncTask-");
        executor.setKeepAliveSeconds(60); // thời gian sống tối đa của một thread
        executor.initialize();
        return executor;
    }
}
