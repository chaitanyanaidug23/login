package org.example.controllers;

import org.example.models.Course;
import org.example.models.Grade;
import org.example.models.QuizCreationRequest;
import org.example.models.ApiResponse;
import org.example.services.FacultyService;
import org.example.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Date;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping("")
    public String facultyDashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String facultyId = auth.getName(); // Assumes the username is the faculty ID
        ApiResponse<List<Course>> apiResponse = facultyService.getCourses(facultyId);
        if (apiResponse.isSuccess()) {
            model.addAttribute("courses", apiResponse.getData());
        } else {
            model.addAttribute("error", apiResponse.getMessage());
        }
        return "faculty";  // Assumes you have a faculty.html in the /resources/templates directory
    }

    @PreAuthorize("hasAuthority('ROLE_FACULTY')")
    @GetMapping("/api/courses")
    public ResponseEntity<ApiResponse<List<Course>>> getCourses() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String facultyId = auth.getName();
        ApiResponse<List<Course>> response = facultyService.getCourses(facultyId);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('ROLE_FACULTY')")
    @PostMapping("/api/course/{courseId}/quizzes")
    public ResponseEntity<ApiResponse<Boolean>> addQuiz(@PathVariable String courseId, @Valid @RequestBody QuizCreationRequest quizRequest) {
        ApiResponse<Boolean> result = facultyService.addQuiz(courseId, quizRequest.getQuestions(), quizRequest.getTitle(), quizRequest.getDuration(), quizRequest.isGraded());
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PreAuthorize("hasAuthority('ROLE_FACULTY')")
    @PostMapping("/api/course/{courseId}/syllabus")
    public ResponseEntity<ApiResponse<Boolean>> updateSyllabus(@PathVariable String courseId, @RequestBody String syllabusContent) {
        ApiResponse<Boolean> updated = facultyService.updateSyllabus(courseId, syllabusContent);
        return ResponseEntity.status(updated.isSuccess() ? 200 : 400).body(updated);
    }

    @PreAuthorize("hasAuthority('ROLE_FACULTY')")
    @GetMapping("/api/course/{courseId}/students")
    public ResponseEntity<ApiResponse<List<String>>> getStudentsByCourse(@PathVariable String courseId) {
        ApiResponse<List<String>> studentsResponse = facultyService.getStudentsByCourse(courseId);
        return ResponseEntity.ok(studentsResponse);
    }

    @PreAuthorize("hasAuthority('ROLE_FACULTY')")
    @GetMapping("/api/course/{courseId}/grades")
    public ResponseEntity<ApiResponse<List<Grade>>> getGrades(@PathVariable String courseId) {
        ApiResponse<List<Grade>> gradesResponse = facultyService.getGradesByCourse(courseId);
        return ResponseEntity.ok(gradesResponse);
    }

    @PreAuthorize("hasAuthority('ROLE_FACULTY')")
    @PostMapping("/api/course/{courseId}/student/{studentId}/grade")
    public ResponseEntity<ApiResponse<Boolean>> assignGrade(@PathVariable String courseId, @PathVariable String studentId, @RequestBody String grade) {
        ApiResponse<Boolean> result = facultyService.assignGrade(courseId, studentId, grade);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PreAuthorize("hasAuthority('ROLE_FACULTY')")
    @PostMapping("/api/course/{courseId}/assignments")
    public ResponseEntity<ApiResponse<Boolean>> addAssignment(@PathVariable String courseId, @RequestBody String description, @RequestParam String dueDate) {
        try {
            Date due = DateUtils.parseDate(dueDate);
            ApiResponse<Boolean> result = facultyService.addAssignment(courseId, description, due);
            return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, false, "Invalid date format"));
        }
    }

    @PreAuthorize("hasAuthority('ROLE_FACULTY')")
    @PostMapping("/api/course/{courseId}/announcements")
    public ResponseEntity<ApiResponse<Boolean>> postAnnouncement(@PathVariable String courseId, @RequestBody String announcement) {
        ApiResponse<Boolean> posted = facultyService.postAnnouncement(courseId, announcement);
        return ResponseEntity.status(posted.isSuccess() ? 200 : 400).body(posted);
    }
}
