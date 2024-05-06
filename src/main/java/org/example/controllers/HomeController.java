package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";  // This should match the name of your home view template
    }

//    // Method to display the faculty dashboard
//    @GetMapping("/faculty")
//    public String facultyDashboard() {
//        return "faculty";  // This assumes you are using Thymeleaf and your faculty.html is under src/main/resources/templates
//    }
}
