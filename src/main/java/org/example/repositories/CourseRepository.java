package org.example.repositories;

import org.example.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> findByFacultyIdAndSemester(String facultyId, String semester);
}
