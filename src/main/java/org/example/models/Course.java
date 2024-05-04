package org.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Course {
    @Id
    private String id;
    private String name;
    private String semester;
    private String facultyId;

    // Constructor
    public Course() {}

    public Course(String id, String name, String semester, String facultyId) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.facultyId = facultyId;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSemester() {
        return semester;
    }

    public String getFacultyId() {
        return facultyId;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }
}
