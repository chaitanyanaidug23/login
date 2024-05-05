package org.example.models;

import java.util.List;

public class Quiz {
    private String title;
    private int duration; // Duration in minutes
    private boolean isGraded;
    private List<String> questions; // Assuming questions are just strings for simplicity.

    // Constructor for the Quiz class with parameters for all fields
    public Quiz(String title, int duration, boolean isGraded, List<String> questions) {
        this.title = title;
        this.duration = duration;
        this.isGraded = isGraded;
        this.questions = questions;
    }

    // Getters and Setters for each property
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isGraded() {
        return isGraded;
    }

    public void setGraded(boolean graded) {
        isGraded = graded;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }
}
