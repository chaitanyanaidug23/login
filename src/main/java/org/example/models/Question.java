package org.example.models;
import java.util.List;
public class Question {
    private String questionText;
    private List<String> options;
    private String correctAnswer;
    private int points;

    public Question(String questionText, List<String> options, String correctAnswer, int points) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }

    // Getters and setters
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
