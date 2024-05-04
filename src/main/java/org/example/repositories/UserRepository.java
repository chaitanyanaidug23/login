package org.example.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.example.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
