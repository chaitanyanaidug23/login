//package org.example;
//
//import org.example.models.User;
//import org.example.repositories.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.ConfigurableApplicationContext;
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
//            List<User> users = Arrays.asList(
//                    new User("alice", passwordEncoder.encode("password123")),
//                    new User("bob", passwordEncoder.encode("password456")),
//                    new User("charlie", passwordEncoder.encode("password789"))
//            );
//
//            // Save to MongoDB
//            userRepository.saveAll(users);
//            System.out.println("Inserted mock data: " + users.size() + " users.");
//        };
//    }
//}
