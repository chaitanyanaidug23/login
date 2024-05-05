package org.example.models;

import java.util.List;

public class Assignment {
    private String description;
    private String dueDate;

    public Assignment(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    // Getters and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
