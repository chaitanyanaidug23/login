package org.example;

import org.example.models.*;
import org.example.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                                    GradeRepository gradeRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Users
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

            // Faculties
            List<Faculty> faculties = Arrays.asList(
                    new Faculty("Bob", "bob@example.com", "Computer Science", new ArrayList<>()),
                    new Faculty("David", "david@example.com", "Mathematics", new ArrayList<>()),
                    new Faculty("Frank", "frank@example.com", "Biology", new ArrayList<>()),
                    new Faculty("Harry", "harry@example.com", "Chemistry", new ArrayList<>()),
                    new Faculty("Irene", "irene@example.com", "Physics", new ArrayList<>())
            );
            facultyRepository.saveAll(faculties);

            // Courses
            List<Course> courses = Arrays.asList(
                    new Course("Math 101", "Spring 2024", faculties.get(1).getId(), true, "Introductory Math", "Detailed Syllabus Content",
                            Arrays.asList("Assignment 1", "Assignment 2"), Arrays.asList("Quiz 1", "Quiz 2"),
                            Arrays.asList("Welcome to Math 101"), new ArrayList<>(), new ArrayList<>(), new Date(), new Date()),
                    new Course("CompSci 101", "Spring 2024", faculties.get(0).getId(), true, "Introduction to Computer Science", "Complete Syllabus Here",
                            Arrays.asList("Assignment 3", "Assignment 4"), Arrays.asList("Quiz 3", "Quiz 4"),
                            Arrays.asList("First Class Meeting"), new ArrayList<>(), new ArrayList<>(), new Date(), new Date()),
                    new Course("Biology 101", "Fall 2024", faculties.get(2).getId(), true, "Foundations of Biology", "Biology Course Outline",
                            Arrays.asList("Assignment 5", "Assignment 6"), Arrays.asList("Quiz 5", "Quiz 6"),
                            Arrays.asList("Lab Safety Introduction"), new ArrayList<>(), new ArrayList<>(), new Date(), new Date()),
                    new Course("Chemistry 101", "Fall 2024", faculties.get(3).getId(), true, "Chemistry Principles", "Chemistry Syllabus",
                            Arrays.asList("Assignment 7", "Assignment 8"), Arrays.asList("Quiz 7", "Quiz 8"),
                            Arrays.asList("Chemical Handling"), new ArrayList<>(), new ArrayList<>(), new Date(), new Date()),
                    new Course("Physics 101", "Winter 2024", faculties.get(4).getId(), true, "Physics for Beginners", "Physics Course Details",
                            Arrays.asList("Assignment 9", "Assignment 10"), Arrays.asList("Quiz 9", "Quiz 10"),
                            Arrays.asList("Introduction to Mechanics"), new ArrayList<>(), new ArrayList<>(), new Date(), new Date())
            );
            courseRepository.saveAll(courses);

            // Students
            List<Student> students = Arrays.asList(
                    new Student("Charlie", "charlie@example.com", courses.get(0).getId()),
                    new Student("Eve", "eve@example.com", courses.get(1).getId()),
                    new Student("Grace", "grace@example.com", courses.get(2).getId()),
                    new Student("Irene", "irene@example.com", courses.get(3).getId()),
                    new Student("Alice", "alice@example.com", courses.get(4).getId())
            );
            studentRepository.saveAll(students);

            // Grades
            List<Grade> grades = Arrays.asList(
                    new Grade(students.get(0).getId(), courses.get(0).getId(), courses.get(0).getAssignments().get(0), null, "A+", new Date()),
                    new Grade(students.get(1).getId(), courses.get(1).getId(), courses.get(1).getAssignments().get(0), null, "B+", new Date()),
                    new Grade(students.get(2).getId(), courses.get(2).getId(), courses.get(2).getAssignments().get(0), null, "C+", new Date()),
                    new Grade(students.get(3).getId(), courses.get(3).getId(), courses.get(3).getAssignments().get(0), null, "D+", new Date()),
                    new Grade(students.get(4).getId(), courses.get(4).getId(), courses.get(4).getAssignments().get(0), null, "F", new Date())
            );
            gradeRepository.saveAll(grades);

            System.out.println("Mock data inserted for all collections.");
        };
    }
}
