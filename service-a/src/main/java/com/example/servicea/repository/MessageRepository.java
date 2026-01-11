package com.example.servicea.repository;

import com.example.servicea.model.MessageLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<MessageLog, String> {
}