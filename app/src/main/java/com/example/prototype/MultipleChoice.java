package com.example.prototype;

import java.util.Arrays;

public class MultipleChoice implements Question {
    public MultipleChoice(String question, String[] answers, int correct) {
        this.question = question;
        this.answers = answers;
        this.correct = correct;
    }
    public MultipleChoice(String[] data) {
        this.question = data[0];
        this.correct = Integer.parseInt(data[1]);
        this.answers = Arrays.copyOfRange(data, 2, data.length);
    }
    public String getQuestion() {
        return question;
    }
    public String[] getAnswers() {
        return answers;
    }
    public int getCorrect() {
        return correct;
    }
    public void use(Visitor v) {
        v.on(this);
    }
    private String question;
    private String[] answers;
    private int correct;
}