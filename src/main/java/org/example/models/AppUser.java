package org.example.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Document(collection = "users")
public class AppUser {
    @Id
    private String id;

    @NotEmpty(message = "Username cannot be empty")
    private String username;

    private String password;

    @Email(message = "Invalid email format")
    private String email;

    private String roles; // Simplified: assuming a single role for simplicity

    public AppUser() {}

    public AppUser(String username, String password, String email, String roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
