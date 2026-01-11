package com.example.servicea.service;

import com.example.servicea.model.MessageLog;
import com.example.servicea.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final MessageRepository repository;
    private final RestTemplate restTemplate; // Внедряем RestTemplate

    @KafkaListener(topics = "in", groupId = "service-a-group")
    public void listen(String messageBody) {
        log.info("📥 Service A получил сообщение: {}", messageBody);

        // 1. Сохраняем в MongoDB
        MessageLog logEntry = new MessageLog(null, messageBody, LocalDateTime.now());
        repository.save(logEntry);
        log.info("✅ Сохранено в MongoDB");

        // 2. Отправляем в Service B
        try {
            String serviceBUrl = "http://localhost:8082/api/process";

            // Формируем JSON для отправки (как мы делали в requests.http)
            Map<String, String> request = new HashMap<>();
            request.put("message", messageBody);
            request.put("type", "important"); // Помечаем как важное, чтобы попало в Redis

            restTemplate.postForObject(serviceBUrl, request, String.class);
            log.info("🚀 Отправлено в Service B");

        } catch (Exception e) {
            log.error("❌ Ошибка при вызове Service B: {}", e.getMessage());
        }
    }
}