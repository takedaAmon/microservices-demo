package com.example.servicec.repository;

import com.example.servicec.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    // Нам не нужно писать методы save(), findAll() и т.д.
    // Spring Data JPA уже реализовал их за нас.
}