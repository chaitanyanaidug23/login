package org.example;

import org.example.models.*;
import org.example.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class MockDataGenerator {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MockDataGenerator.class, args);
        SpringApplication.exit(context);  // Close the application context after insertion
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository, FacultyRepository facultyRepository,
                                    CourseRepository courseRepository, StudentRepository studentRepository,
                                    GradeRepository gradeRepository, AssignmentRepository assignmentRepository,
                                    QuizRepository quizRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

            // Clear previous data
            userRepository.deleteAll();
            facultyRepository.deleteAll();
            courseRepository.deleteAll();
            studentRepository.deleteAll();
            gradeRepository.deleteAll();
            assignmentRepository.deleteAll();
            quizRepository.deleteAll();

            // Generate Users
            List<User> users = Arrays.asList(
                    new User("alice", passwordEncoder.encode("password123"), "alice@example.com", "ROLE_ADMIN"),
                    new User("bob", passwordEncoder.encode("password456"), "bob@example.com", "ROLE_FACULTY"),
                    new User("charlie", passwordEncoder.encode("password789"), "charlie@example.com", "ROLE_STUDENT"),
                    new User("david", passwordEncoder.encode("password101"), "david@example.com", "ROLE_FACULTY"),
                    new User("eve", passwordEncoder.encode("password202"), "eve@example.com", "ROLE_STUDENT"),
                    new User("frank", passwordEncoder.encode("password303"), "frank@example.com", "ROLE_FACULTY"),
                    new User("grace", passwordEncoder.encode("password404"), "grace@example.com", "ROLE_STUDENT"),
                    new User("harry", passwordEncoder.encode("password505"), "harry@example.com", "ROLE_FACULTY"),
                    new User("irene", passwordEncoder.encode("password606"), "irene@example.com", "ROLE_STUDENT")
            );
            userRepository.saveAll(users);

            // Generate Faculties and Courses Simultaneously
            List<Faculty> faculties = new ArrayList<>();
            List<Course> courses = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                Faculty faculty = new Faculty("Faculty " + i, "faculty" + i + "@example.com", "Department " + i, new ArrayList<>());
                Course course = new Course("Course " + i, "Spring 2024", faculty.getId(), true,
                        "Description of Course " + i, "Detailed Syllabus of Course " + i, new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Date(), new Date());
                faculty.getCourses().add(course.getId());
                faculties.add(faculty);
                courses.add(course);
            }
            facultyRepository.saveAll(faculties);
            courseRepository.saveAll(courses);

            // Generate Students and enroll them in courses
            List<Student> students = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Student student = new Student("Student " + i, "student" + i + "@example.com", courses.get(i % courses.size()).getId());
                students.add(student);
            }
            studentRepository.saveAll(students);

            // Generate Assignments for each course
            List<Assignment> assignments = new ArrayList<>();
            for (Course course : courses) {
                for (int i = 1; i <= 2; i++) {
                    Date dueDate = sdf.parse("2024-05-15T23:59:00Z");
                    Date postedDate = sdf.parse("2024-04-20T00:00:00Z");
                    Assignment assignment = new Assignment(course.getId(), "Assignment " + i + " for " + course.getName(), dueDate, postedDate);
                    assignments.add(assignment);
                    course.getAssignments().add(assignment.getId());
                }
            }
            assignmentRepository.saveAll(assignments);

            // Generate Quizzes for each course
            List<Quiz> quizzes = new ArrayList<>();
            for (Course course : courses) {
                for (int i = 1; i <= 2; i++) {
                    Quiz quiz = new Quiz(course.getId(), "Quiz " + i + " on " + course.getName(), 30, true,
                            Arrays.asList(new Question("What is the main topic of " + course.getName() + "?", Arrays.asList("Option A", "Option B", "Option C"), "Option A", 5)));
                    quizzes.add(quiz);
                    course.getQuizzes().add(quiz.getId());
                }
            }
            quizRepository.saveAll(quizzes);

            // Generate Grades for students
            List<Grade> grades = new ArrayList<>();
            for (Student student : students) {
                Grade grade = new Grade(student.getId(), student.getCourseId(), null, null, "A", new Date());
                grades.add(grade);
            }
            gradeRepository.saveAll(grades);

            System.out.println("Mock data inserted for all collections.");
        };
    }
}
