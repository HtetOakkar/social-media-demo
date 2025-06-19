package com.htetoakkar.socialmedia.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
    // This class enables asynchronous method execution in the application.
    // You can define beans or configurations related to async processing here if needed.

    @Bean(name = "threadPoolTaskExecutor")
    AsyncTaskExecutor executor() {
        // This method creates a ThreadPoolTaskExecutor bean with specific configurations.
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(50);
        executor.setAwaitTerminationSeconds(30);
        executor.setThreadNamePrefix("Thread-");
        executor.setAllowCoreThreadTimeOut(true);
        return executor;
    }
}
