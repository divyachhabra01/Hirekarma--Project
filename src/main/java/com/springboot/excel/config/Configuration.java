package com.springboot.excel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootConfiguration
public class Configuration {
    @Value("${thread.max.pool.size}")
    private int threadPoolSize;

    @Bean
    ThreadPoolExecutor threadPoolExecutor() {
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(threadPoolSize);
    }
}
