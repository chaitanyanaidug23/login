package org.example.repositories;

import org.example.models.Grade;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GradeRepository extends MongoRepository<Grade, String> {
    // Additional custom queries can be defined here as needed

    // Example: Find grades by student ID
    List<Grade> findByStudentId(String studentId);

    // Example: Find grades for a specific course
    List<Grade> findByCourseId(String courseId);

    // Example: Find grades related to a specific assignment
    List<Grade> findByAssignmentId(String assignmentId);
    List<Grade> findAllById(Iterable<String> ids);

}
