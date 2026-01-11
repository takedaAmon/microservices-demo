package com.example.servicec.service;

import com.example.servicec.model.DataEntity;
import com.example.servicec.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class FinalService {

    private final DataRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void finalizeData(String payload) {
        log.info("🔹 Service C получил данные: {}", payload);

        // 1. Сохраняем в PostgreSQL
        DataEntity entity = new DataEntity(null, payload, LocalDateTime.now());
        repository.save(entity);
        log.info("💾 Сохранено в PostgreSQL (ID: {})", entity.getId());

        // 2. Отправляем в Kafka (топик 'out')
        kafkaTemplate.send("out", "Finalized: " + payload);
        log.info("📤 Отправлено в Kafka topic 'out'");
    }
}