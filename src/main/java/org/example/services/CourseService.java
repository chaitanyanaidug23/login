package org.example.services;

import org.example.models.Course;
import org.example.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> findCourseById(String id) {
        return courseRepository.findById(id);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }
}
