package org.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {
    @Id
    private String id;
    private String name;
    private String semester;
    private String facultyId;  // Add this field to represent the associated faculty
    @DBRef
    private Faculty faculty;
    // Constructors, getters, and setters
    public Course() {
    }

    public Course(String id, String name, String semester, String facultyId) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.facultyId = facultyId;
    }

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

    public Faculty getFaculty() {
        return faculty;
    }


    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }
}
