package org.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashSet;


import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private Set<String> roles = new HashSet<>();

    // Constructors, getters, and setters

    public User() {}

    public User(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Single role getter for simplicity in some cases
    public String getRole() {
        return roles != null && !roles.isEmpty() ? roles.iterator().next() : null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Single role setter for simplicity
    public void setRole(String role) {
        this.roles = Set.of(role); // This replaces all existing roles with the new single role
    }

    // Standard getters and setters for other fields and roles
    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
