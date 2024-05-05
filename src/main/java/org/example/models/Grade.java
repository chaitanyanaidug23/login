package org.example.models;

public class Grade {
    private String studentId;
    private String courseId;
    private String assignmentId; // Optional, depending on whether grades are tied to specific assignments
    private String value; // The grade itself, e.g., "A+", "B", etc.

    public Grade(String studentId, String courseId, String assignmentId, String value) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.assignmentId = assignmentId;
        this.value = value;
    }

    // Getters and setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
