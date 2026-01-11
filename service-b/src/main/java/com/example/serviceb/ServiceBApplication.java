package com.example.serviceb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ServiceBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceBApplication.class, args);
    }

    // Создаем бин здесь, чтобы Spring мог отдать его в ProcessingService
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}