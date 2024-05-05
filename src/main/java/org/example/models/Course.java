package org.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotBlank;

@Document(collection = "courses")
public class Course {
    @Id
    private String id;

    @NotBlank(message = "Course name must not be empty")
    private String name;

    @NotBlank(message = "Semester must not be empty")
    private String semester;

    private String facultyId; // Simple reference to Faculty for queries

    @DBRef
    private Faculty faculty; // Full DB reference to a Faculty document

    private boolean published; // Indicates if the course is currently visible to students

    @NotBlank(message = "Description must not be empty")
    private String description; // Brief description of the course

    private String syllabus; // Detailed syllabus content

    private List<String> assignments = new ArrayList<>(); // List of assignments
    private List<String> quizzes = new ArrayList<>(); // List of quizzes
    private List<String> announcements = new ArrayList<>(); // Course-related announcements
    private List<String> enrolledStudents; // Simplified list of enrolled students
    private List<Grade> grades; // List of grades for assignments and exams

    @CreatedDate
    private Date createdDate; // The date when the course document was created

    @LastModifiedDate
    private Date updatedDate; // The date when the course document was last updated

    // Constructors
    public Course() {}

    public Course(String id, String name, String semester, String facultyId, boolean published, String description,
                  String syllabus, List<String> assignments, List<String> quizzes, List<String> announcements) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.facultyId = facultyId;
        this.published = published;
        this.description = description;
        this.syllabus = syllabus;
        this.assignments = assignments;
        this.quizzes = quizzes;
        this.announcements = announcements;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public List<String> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<String> assignments) {
        this.assignments = assignments;
    }

    public List<String> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<String> quizzes) {
        this.quizzes = quizzes;
    }

    public List<String> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<String> announcements) {
        this.announcements = announcements;
    }

    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<String> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
