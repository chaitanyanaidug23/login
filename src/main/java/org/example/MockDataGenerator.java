//package org.example;
//
//import org.example.models.*;
//import org.example.repositories.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.*;
//
//@SpringBootApplication
//public class MockDataGenerator {
//
//    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(MockDataGenerator.class, args);
//        SpringApplication.exit(context);  // Close the application context after insertion
//    }
//    @Bean
//    public CommandLineRunner runner(UserRepository userRepository, FacultyRepository facultyRepository,
//                                    CourseRepository courseRepository, StudentRepository studentRepository,
//                                    GradeRepository gradeRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            // Users
//            List<User> users = Arrays.asList(
//                    new User("alice", passwordEncoder.encode("password123"), "alice@example.com", "ROLE_ADMIN"),
//                    new User("bob", passwordEncoder.encode("password456"), "bob@example.com", "ROLE_FACULTY"),
//                    new User("charlie", passwordEncoder.encode("password789"), "charlie@example.com", "ROLE_STUDENT"),
//                    new User("david", passwordEncoder.encode("password101"), "david@example.com", "ROLE_FACULTY"),
//                    new User("eve", passwordEncoder.encode("password202"), "eve@example.com", "ROLE_STUDENT"),
//                    new User("frank", passwordEncoder.encode("password303"), "frank@example.com", "ROLE_FACULTY"),
//                    new User("grace", passwordEncoder.encode("password404"), "grace@example.com", "ROLE_STUDENT"),
//                    new User("harry", passwordEncoder.encode("password505"), "harry@example.com", "ROLE_FACULTY"),
//                    new User("irene", passwordEncoder.encode("password606"), "irene@example.com", "ROLE_STUDENT")
//            );
//            userRepository.saveAll(users);
//
//            // Faculties and Courses Created Simultaneously
//            List<Faculty> faculties = new ArrayList<>();
//            List<Course> courses = new ArrayList<>();
//
//            faculties.add(new Faculty("Bob", "bob@example.com", "Computer Science", new ArrayList<>()));
//            faculties.add(new Faculty("David", "david@example.com", "Mathematics", new ArrayList<>()));
//            faculties.add(new Faculty("Frank", "frank@example.com", "Biology", new ArrayList<>()));
//            faculties.add(new Faculty("Harry", "harry@example.com", "Chemistry", new ArrayList<>()));
//            faculties.add(new Faculty("Irene", "irene@example.com", "Physics", new ArrayList<>()));
//
//            // Ensure courses are assigned at creation
//            for (int i = 0; i < faculties.size(); i++) {
//                Course course = new Course(
//                        "Course " + (i + 101),
//                        "2024",
//                        faculties.get(i).getId(), // Associate faculty
//                        true,
//                        "Description for course " + (i + 101),
//                        "Syllabus for course " + (i + 101),
//                        new ArrayList<>(),
//                        new ArrayList<>(),
//                        new ArrayList<>(),
//                        new ArrayList<>(),
//                        new ArrayList<>(),
//                        new Date(),
//                        new Date()
//                );
//                courses.add(course);
//                faculties.get(i).getCourses().add(course.getId()); // Link course back to faculty
//            }
//
//            courseRepository.saveAll(courses);
//            facultyRepository.saveAll(faculties);
//
//            // Students
//            List<Student> students = Arrays.asList(
//                    new Student("Charlie", "charlie@example.com", courses.get(0).getId()),
//                    new Student("Eve", "eve@example.com", courses.get(1).getId()),
//                    new Student("Grace", "grace@example.com", courses.get(2).getId()),
//                    new Student("Irene", "irene@example.com", courses.get(3).getId()),
//                    new Student("Alice", "alice@example.com", courses.get(4).getId())
//            );
//            studentRepository.saveAll(students);
//
//            // Grades
//            List<Grade> grades = Arrays.asList(
//                    new Grade(students.get(0).getId(), courses.get(0).getId(), null, null, "A+", new Date()),
//                    new Grade(students.get(1).getId(), courses.get(1).getId(), null, null, "B+", new Date()),
//                    new Grade(students.get(2).getId(), courses.get(2).getId(), null, null, "C+", new Date()),
//                    new Grade(students.get(3).getId(), courses.get(3).getId(), null, null, "D+", new Date()),
//                    new Grade(students.get(4).getId(), courses.get(4).getId(), null, null, "F", new Date())
//            );
//            gradeRepository.saveAll(grades);
//
//            System.out.println("Mock data inserted for all collections.");
//        };
//    }
//
//}
