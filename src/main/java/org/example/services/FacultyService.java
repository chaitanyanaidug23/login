package org.example.services;

import org.example.models.*;
import org.example.repositories.CourseRepository;
import org.example.repositories.FacultyRepository;
import org.example.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private GradeRepository gradeRepository;

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
    }

    public ApiResponse<List<Course>> getCourses(String facultyId) {
        List<Course> courses = courseRepository.findByFacultyId(facultyId);
        if (courses != null && !courses.isEmpty()) {
            return new ApiResponse<>(true, courses, "Courses retrieved successfully");
        } else {
            return new ApiResponse<>(false, null, "No courses found or faculty ID not valid");
        }
    }


    @Transactional
    public ApiResponse<Boolean> updateSyllabus(String courseId, String syllabusContent) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            course.setSyllabus(syllabusContent);
            courseRepository.save(course);
            return new ApiResponse<>(true, true, "Syllabus updated successfully.");
        }
        return new ApiResponse<>(false, false, "Course not found.");
    }

    public ApiResponse<List<String>> getStudentsByCourse(String courseId) {
        return courseRepository.findById(courseId)
                .map(course -> new ApiResponse<>(true, course.getEnrolledStudents(), "Students retrieved successfully."))
                .orElse(new ApiResponse<>(false, null, "Course not found."));
    }

    public ApiResponse<List<Grade>> getGradesByCourse(String courseId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent() && courseOpt.get().getGrades() != null) {
            List<Grade> grades = gradeRepository.findAllById(courseOpt.get().getGrades());
            return new ApiResponse<>(true, grades, "Grades retrieved successfully.");
        }
        return new ApiResponse<>(false, null, "No grades found for the course.");
    }

    @Transactional
    public ApiResponse<Boolean> assignGrade(String courseId, String studentId, String grade) {
        // Implementation needed here for grading logic
        return new ApiResponse<>(false, false, "Method not implemented yet.");
    }

    @Transactional
    public ApiResponse<Boolean> addAssignment(String courseId, String description, String dueDate) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    Assignment newAssignment = new Assignment(description, dueDate);
                    course.getAssignments().add(String.valueOf(newAssignment));
                    courseRepository.save(course);
                    return new ApiResponse<>(true, true, "Assignment added successfully.");
                })
                .orElse(new ApiResponse<>(false, false, "Course not found."));
    }

    @Transactional
    public ApiResponse<Boolean> addQuiz(String courseId, List<String> questions, String title, int duration, boolean isGraded) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    Quiz newQuiz = new Quiz(title, duration, isGraded, questions);
                    course.getQuizzes().add(String.valueOf(newQuiz));
                    courseRepository.save(course);
                    return new ApiResponse<>(true, true, "Quiz added successfully.");
                })
                .orElse(new ApiResponse<>(false, false, "Course not found."));
    }

    @Transactional
    public ApiResponse<Boolean> postAnnouncement(String courseId, String announcement) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            course.getAnnouncements().add(announcement);
            courseRepository.save(course);
            return new ApiResponse<>(true, true, "Announcement posted successfully.");
        }
        return new ApiResponse<>(false, false, "Course not found.");
    }
}
