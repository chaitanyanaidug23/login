package org.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "faculties")
public class Faculty {
    @Id
    private String id;
    private String name;
    private String email;
    private String department;
    private List<String> courses; // Assuming courses are referenced by their IDs as strings

    public Faculty() {
    }

    public Faculty(String name, String email, String department, List<String> courses) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.courses = courses;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
