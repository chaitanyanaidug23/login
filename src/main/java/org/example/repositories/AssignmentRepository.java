package org.example.repositories;

import org.example.models.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssignmentRepository extends MongoRepository<Assignment, String> {
    // Custom query methods can be defined here
}
