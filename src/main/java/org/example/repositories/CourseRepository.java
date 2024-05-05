package org.example.repositories;

import org.example.models.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> findByFacultyIdAndSemester(String facultyId, String semester);

    // Find courses by name using a case-insensitive search
    List<Course> findByNameIgnoreCaseContaining(String name);

    // Find all published courses for a faculty in a specific semester
    List<Course> findByFacultyIdAndSemesterAndPublished(String facultyId, String semester, boolean published);

    List<Course> findByFacultyId(String facultyId);
    // Custom query using @Query for more complex scenarios
    @Query("{ 'facultyId' : ?0, 'published' : true }")
    List<Course> findPublishedCoursesByFacultyId(String facultyId);
}

