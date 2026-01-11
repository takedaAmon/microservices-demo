package com.example.servicec.controller;

import com.example.servicec.entity.MessageEntity;
import com.example.servicec.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/save")
@RequiredArgsConstructor // Создаст конструктор для внедрения зависимостей
public class MessageController {

    private final MessageRepository repository;
    // KafkaTemplate — это стандартный инструмент Spring для отправки сообщений
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public String save(@RequestBody String message) {
        // 1. Сохраняем в PostgreSQL
        MessageEntity entity = new MessageEntity(message);
        repository.save(entity);

        // 2. Публикуем сообщение в Kafka в топик "out"
        // (Топик создастся автоматически при первой отправке)
        kafkaTemplate.send("out", "Service C saved message: " + message);

        return "Сообщение сохранено в БД и отправлено в Kafka!";
    }
}