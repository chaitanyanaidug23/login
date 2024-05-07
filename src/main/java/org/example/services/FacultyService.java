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
    private StudentRepository studentRepository;
    @Autowired
    private QuizRepository quizRepository;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    // Retrieve all courses for a given faculty
    public ApiResponse<List<Course>> getCourses(String facultyId) {
        List<Course> courses = courseRepository.findByFacultyId(facultyId);
        if (courses.isEmpty()) {
            return new ApiResponse<>(false, null, "No courses found or faculty ID not valid");
        }
        return new ApiResponse<>(true, courses, "Courses retrieved successfully");
    }

    // Update syllabus content for a course
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

    // Retrieve all students enrolled in a specific course
    public ApiResponse<List<String>> getStudentsByCourse(String courseId) {
        return courseRepository.findById(courseId)
                .map(course -> new ApiResponse<>(true, course.getEnrolledStudents(), "Students retrieved successfully."))
                .orElse(new ApiResponse<>(false, null, "Course not found."));
    }

    // Retrieve all grades for a specific course
    public ApiResponse<List<Grade>> getGradesByCourse(String courseId) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent() && !courseOpt.get().getGrades().isEmpty()) {
            List<Grade> grades = gradeRepository.findAllById(courseOpt.get().getGrades());
            return new ApiResponse<>(true, grades, "Grades retrieved successfully.");
        }
        return new ApiResponse<>(false, null, "No grades found for the course.");
    }

    // Assign a new grade to a student for a course
    @Transactional
    public ApiResponse<Boolean> assignGrade(String courseId, String studentId, String gradeValue) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        Optional<Student> studentOpt = studentRepository.findById(studentId);

        if (!courseOpt.isPresent() || !studentOpt.isPresent()) {
            return new ApiResponse<>(false, false, "Course or student not found.");
        }

        Grade grade = new Grade(null, studentId, courseId, null, null, gradeValue, new Date());
        gradeRepository.save(grade);

        return new ApiResponse<>(true, true, "Grade assigned successfully.");
    }

    // Add a new assignment to a course
    @Transactional
    public ApiResponse<Boolean> addAssignment(String courseId, String description, Date dueDate) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (!courseOpt.isPresent()) {
            return new ApiResponse<>(false, false, "Course not found.");
        }
        Assignment newAssignment = new Assignment(null, courseId, description, dueDate, new Date());
        assignmentRepository.save(newAssignment);
        Course course = courseOpt.get();
        course.getAssignments().add(newAssignment.getId());
        courseRepository.save(course);
        return new ApiResponse<>(true, true, "Assignment added successfully.");
    }

    // Add a new quiz to a course
    @Transactional
    public ApiResponse<Boolean> addQuiz(String courseId, List<String> questionTitles, String title, int duration, boolean isGraded) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (!courseOpt.isPresent()) {
            return new ApiResponse<>(false, false, "Course not found.");
        }
        List<Question> questions = createQuestions(questionTitles);
        Quiz newQuiz = new Quiz(null, courseId, title, duration, isGraded, questions);
        quizRepository.save(newQuiz);
        Course course = courseOpt.get();
        course.getQuizzes().add(newQuiz.getId());
        courseRepository.save(course);
        return new ApiResponse<>(true, true, "Quiz added successfully.");
    }

    // Helper method to create questions from titles
    private List<Question> createQuestions(List<String> titles) {
        return titles.stream()
                .map(title -> new Question(title, Arrays.asList("Option 1", "Option 2", "Option 3"), "Option 1", 5))
                .collect(Collectors.toList());
    }

    // Post an announcement for a course
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
