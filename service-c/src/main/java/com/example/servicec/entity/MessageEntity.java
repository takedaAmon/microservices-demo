package com.example.servicec.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // Указывает, что этот класс связан с БД
@Table(name = "messages") // Имя таблицы, которую мы создали в Liquibase
@Getter // Генерирует геттеры через Lombok
@Setter // Генерирует сеттеры через Lombok
@NoArgsConstructor // Пустой конструктор (обязателен для Hibernate)
@AllArgsConstructor // Конструктор со всеми полями
public class MessageEntity {

    @Id // Помечает поле как Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Указывает на автоинкремент (как в Liquibase)
    private Long id;

    private String content;

    // Конструктор для удобства, чтобы не передавать ID вручную
    public MessageEntity(String content) {
        this.content = content;
    }
}