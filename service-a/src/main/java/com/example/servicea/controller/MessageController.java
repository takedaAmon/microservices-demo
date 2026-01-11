package com.example.servicea.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam("msg") String message) {
        // "messages-topic" — это имя топика, которое должен слушать Service C
        kafkaTemplate.send("messages-topic", message);
        return "Сообщение отправлено в Kafka: " + message;
    }
}