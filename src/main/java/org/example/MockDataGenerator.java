//package org.example;
//
//import org.example.models.User;
//import org.example.repositories.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Arrays;
//import java.util.List;
//
//@SpringBootApplication
//public class MockDataGenerator {
//
//    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(MockDataGenerator.class, args);
//        SpringApplication.exit(context);  // Close the application context after insertion
//    }
//
//    @Bean
//    public CommandLineRunner runner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            // Generate Users
//            List<User> users = Arrays.asList(
//                    new User("u1", "admin", passwordEncoder.encode("password123"), "admin@example.edu", "ROLE_ADMIN"),
//                    new User("u2", "faculty1", passwordEncoder.encode("password456"), "faculty1@example.edu", "ROLE_FACULTY"),
//                    new User("u3", "faculty2", passwordEncoder.encode("password789"), "faculty2@example.edu", "ROLE_FACULTY"),
//                    new User("u4", "faculty3", passwordEncoder.encode("password101"), "faculty3@example.edu", "ROLE_FACULTY"),
//                    new User("u5", "faculty4", passwordEncoder.encode("password102"), "faculty4@example.edu", "ROLE_FACULTY"),
//                    new User("u6", "faculty5", passwordEncoder.encode("password103"), "faculty5@example.edu", "ROLE_FACULTY"),
//                    new User("u7", "student1", passwordEncoder.encode("password104"), "student1@example.edu", "ROLE_STUDENT"),
//                    new User("u8", "student2", passwordEncoder.encode("password105"), "student2@example.edu", "ROLE_STUDENT"),
//                    new User("u9", "student3", passwordEncoder.encode("password106"), "student3@example.edu", "ROLE_STUDENT"),
//                    new User("u10", "student4", passwordEncoder.encode("password107"), "student4@example.edu", "ROLE_STUDENT")
//            );
//            try {
//                userRepository.saveAll(users);
//                System.out.println("Mock data inserted successfully.");
//            } catch (Exception e) {
//                System.err.println("Failed to insert mock data: " + e.getMessage());
//            }
//        };
//    }
//}
