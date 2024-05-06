package org.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "grades")
public class Grade {
    @Id
    private String id;
    private String studentId;
    private String courseId;
    private String assignmentId;
    private String quizId;
    private String value;
    private Date dateAwarded;

    public Grade() {
    }

    public Grade(String studentId, String courseId, String assignmentId, String quizId, String value, Date dateAwarded) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.assignmentId = assignmentId;
        this.quizId = quizId;
        this.value = value;
        this.dateAwarded = dateAwarded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getDateAwarded() {
        return dateAwarded;
    }

    public void setDateAwarded(Date dateAwarded) {
        this.dateAwarded = dateAwarded;
    }
}
