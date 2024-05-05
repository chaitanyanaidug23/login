package org.example.controllers;

import org.example.models.Course;
import org.example.models.Faculty;
import org.example.models.QuizCreationRequest;
import org.example.services.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    // Existing methods for managing faculty...

    // Get list of courses taught by the faculty
    @GetMapping("/{facultyId}/courses")
    public ResponseEntity<List<Course>> getCourses(@PathVariable String facultyId) {
        return ResponseEntity.ok(facultyService.getCourses(facultyId));
    }

    @PostMapping("/course/{courseId}/quizzes")
    public ResponseEntity<?> addQuiz(@PathVariable String courseId, @RequestBody QuizCreationRequest quizRequest) {
        boolean result = facultyService.addQuiz(courseId, quizRequest.getQuestions(), quizRequest.getTitle(), quizRequest.getDuration(), quizRequest.isGraded());
        if (result) {
            return ResponseEntity.ok("Quiz added successfully.");
        }
        return ResponseEntity.badRequest().body("Failed to add quiz.");
    }

    // Add content to the Syllabus section
    @PostMapping("/course/{courseId}/syllabus")
    public ResponseEntity<String> updateSyllabus(@PathVariable String courseId, @RequestBody String syllabusContent) {
        boolean updated = facultyService.updateSyllabus(courseId, syllabusContent);
        if (updated) {
            return ResponseEntity.ok("Syllabus updated successfully.");
        }
        return ResponseEntity.badRequest().body("Failed to update syllabus.");
    }

    @GetMapping("/course/{courseId}/students")
    public ResponseEntity<List<String>> getStudentsByCourse(@PathVariable String courseId) {
        List<String> studentIds = facultyService.getStudentsByCourse(courseId);
        return ResponseEntity.ok(studentIds);
    }

    // View grades for a course
    @GetMapping("/course/{courseId}/grades")
    public ResponseEntity<?> getGrades(@PathVariable String courseId) {
        return ResponseEntity.ok(facultyService.getGradesByCourse(courseId));
    }

    // Assign grades to a student for a course
    @PostMapping("/course/{courseId}/student/{studentId}/grade")
    public ResponseEntity<?> assignGrade(@PathVariable String courseId, @PathVariable String studentId, @RequestBody String grade) {
        boolean result = facultyService.assignGrade(courseId, studentId, grade);
        if (result) {
            return ResponseEntity.ok("Grade assigned successfully.");
        }
        return ResponseEntity.badRequest().body("Failed to assign grade.");
    }

    // Add an assignment to a course
    @PostMapping("/course/{courseId}/assignments")
    public ResponseEntity<?> addAssignment(@PathVariable String courseId, @RequestBody String description, String dueDate) {
        boolean result = facultyService.addAssignment(courseId, description, dueDate);
        if (result) {
            return ResponseEntity.ok("Assignment added successfully.");
        }
        return ResponseEntity.badRequest().body("Failed to add assignment.");
    }


    // Post an announcement in a course
    @PostMapping("/course/{courseId}/announcements")
    public ResponseEntity<?> postAnnouncement(@PathVariable String courseId, @RequestBody String announcement) {
        boolean posted = facultyService.postAnnouncement(courseId, announcement);
        if (posted) {
            return ResponseEntity.ok("Announcement posted successfully.");
        }
        return ResponseEntity.badRequest().body("Failed to post announcement.");
    }
}
