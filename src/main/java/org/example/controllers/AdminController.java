package org.example.controllers;

import org.example.models.Course;
import org.example.models.Faculty;
import org.example.models.Student;
import org.example.repositories.CourseRepository;
import org.example.repositories.FacultyRepository;
import org.example.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private StudentRepository studentRepository;

    // View courses by faculty and semester
    @GetMapping("/courses/{facultyId}/{semester}")
    public ResponseEntity<List<Course>> getCoursesByFacultyAndSemester(
            @PathVariable Long facultyId,
            @PathVariable String semester) {
        List<Course> courses = courseRepository.findByFacultyIdAndSemester(String.valueOf(facultyId), semester);
        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    // Assign a course to a faculty for a new semester
    @PostMapping("/courses/assign")
    public ResponseEntity<Course> assignCourseToFaculty(@RequestBody Course course) {
        if (course.getFaculty() == null || course.getSemester() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Course savedCourse = courseRepository.save(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    // View student list for each course
    @GetMapping("/courses/{courseId}/students")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable Long courseId) {
        Optional<Course> course = courseRepository.findById(String.valueOf(courseId));
        if (!course.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Student> students = studentRepository.findByCourseId(String.valueOf(courseId));
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
