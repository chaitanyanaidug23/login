package org.example.services;

import org.example.models.*;
import org.example.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private QuizRepository quizRepository;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");


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
    // Method to create Question objects from strings
    private List<Question> createQuestions(List<String> titles) {
        return titles.stream()
                .map(title -> new Question(title, Arrays.asList("Option 1", "Option 2", "Option 3"), "Option 1", 5))
                .collect(Collectors.toList());
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
    public ApiResponse<Boolean> addAssignment(String courseId, String description, Date dueDate) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    Assignment newAssignment = new Assignment(course.getId(), description, dueDate, new Date());
                    assignmentRepository.save(newAssignment); // Save the new assignment
                    course.getAssignments().add(newAssignment.getId());
                    courseRepository.save(course);
                    return new ApiResponse<>(true, true, "Assignment added successfully.");
                })
                .orElse(new ApiResponse<>(false, false, "Course not found."));
    }

    @Transactional
    public ApiResponse<Boolean> addQuiz(String courseId, List<String> questionTitles, String title, int duration, boolean isGraded) {
        return courseRepository.findById(courseId)
                .map(course -> {
                    List<Question> questions = createQuestions(questionTitles);
                    Quiz newQuiz = new Quiz(course.getId(), title, duration, isGraded, questions);
                    quizRepository.save(newQuiz); // Save the new quiz
                    course.getQuizzes().add(newQuiz.getId());
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
