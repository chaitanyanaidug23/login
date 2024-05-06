package org.example.services;

import org.example.models.*;
import org.example.repositories.CourseRepository;
import org.example.repositories.FacultyRepository;
import org.example.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Faculty> findAllFaculty() {
        return facultyRepository.findAll();
    }

    public Faculty saveFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> findFacultyById(String id) {
        return facultyRepository.findById(id);
    }

    @Transactional
    public void deleteFaculty(String id) {
        facultyRepository.deleteById(id);
        // Optional: Add logic to handle related data cleanup, if necessary
    }

    public List<Course> getCourses(String facultyId) {
        return courseRepository.findByFacultyId(facultyId);
    }

    @Transactional
    public boolean updateSyllabus(String courseId, String syllabusContent) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        return courseOpt.map(course -> {
            course.setSyllabus(syllabusContent);
            courseRepository.save(course);
            return true;
        }).orElse(false);
    }

    public List<String> getStudentsByCourse(String courseId) {
        return courseRepository.findById(courseId)
                .map(Course::getEnrolledStudents)  // Assuming this returns List<String>
                .orElse(null);
    }


    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> getGradesByCourse(String courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        if (course != null && course.getGrades() != null) {
            return gradeRepository.findAllById(course.getGrades());  // Assuming `course.getGrades()` returns List<String> of Grade IDs
        }
        return new ArrayList<>();
    }


    @Transactional
    public boolean assignGrade(String courseId, String studentId, String grade) {
        // This method should be implemented to modify the grade for a specific student in a specific course
        return true; // Placeholder for actual implementation
    }

    @Transactional
    public boolean addAssignment(String courseId, String description, String dueDate) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        return courseOpt.map(course -> {
            Assignment newAssignment = new Assignment(description, dueDate); // Ensure proper constructors
            course.getAssignments().add(String.valueOf(newAssignment));
            courseRepository.save(course);
            return true;
        }).orElse(false);
    }

    @Transactional
    public boolean addQuiz(String courseId, List<String> questions, String title, int duration, boolean isGraded)
    {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        return courseOpt.map(course -> {
            Quiz newQuiz = new Quiz(title, duration, isGraded, questions);
            course.getQuizzes().add(String.valueOf(newQuiz));
            courseRepository.save(course);
            return true;
        }).orElse(false);
    }

    @Transactional
    public boolean postAnnouncement(String courseId, String announcement) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        return courseOpt.map(course -> {
            course.getAnnouncements().add(announcement);
            courseRepository.save(course);
            return true;
        }).orElse(false);
    }
}
