package org.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Document(collection = "courses")
public class Course {
    @Id
    private String id;
    private String name;
    private String semester;
    private String facultyId;
    private boolean published;
    private String description;
    private String syllabus;
    private List<String> assignments;
    private List<String> quizzes;
    private List<String> announcements;
    private List<String> enrolledStudents;
    private List<String> grades;
    private Date createdDate;
    private Date updatedDate;

    public Course() {
    }

    public Course(String name, String semester, String facultyId, boolean published, String description,
                  String syllabus, List<String> assignments, List<String> quizzes, List<String> announcements,
                  List<String> enrolledStudents, List<String> grades, Date createdDate, Date updatedDate) {
        this.name = name;
        this.semester = semester;
        this.facultyId = facultyId;
        this.published = published;
        this.description = description;
        this.syllabus = syllabus;
        this.assignments = assignments;
        this.quizzes = quizzes;
        this.announcements = announcements;
        this.enrolledStudents = enrolledStudents;
        this.grades = grades;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    // Getters and Setters
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

    public List<String> getGrades() {
        return grades;
    }

    public void setGrades(List<String> grades) {
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
