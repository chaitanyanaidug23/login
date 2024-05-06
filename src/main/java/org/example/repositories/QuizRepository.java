package org.example.repositories;

import org.example.models.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepository extends MongoRepository<Quiz, String> {
    // You can define custom queries here if needed
}
