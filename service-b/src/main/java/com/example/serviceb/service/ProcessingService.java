package com.example.serviceb.service;

import com.example.serviceb.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap; // Импорт для Map
import java.util.Map;
import java.util.concurrent.TimeUnit; // Импорт для времени

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessingService {

    private final StringRedisTemplate redisTemplate;
    private final RestTemplate restTemplate;

    public void process(MessageDto dto) {
        log.info("🔹 Service B получил запрос: {}", dto);

        // Логика Redis
        if ("important".equalsIgnoreCase(dto.getType())) {
            redisTemplate.opsForValue().set(dto.getMessage(), "TRUE", 5, TimeUnit.MINUTES);
            log.info("🔥 Сообщение помечено как ВАЖНОЕ и сохранено в Redis (TTL 5 мин)");
        }

        // Логика отправки в Service C
        try {
            String serviceCUrl = "http://localhost:8083/api/save";

            // Используем HashMap вместо Map.of, чтобы не ругалась IDEA
            Map<String, String> request = new HashMap<>();
            request.put("message", dto.getMessage());

            restTemplate.postForObject(serviceCUrl, request, String.class);
            log.info("🚀 Передано в Service C");
        } catch (Exception e) {
            log.error("❌ Ошибка вызова Service C: " + e.getMessage());
        }
    }
}