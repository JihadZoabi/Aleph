package com.example.prototype;

public class MultipleChoice implements Question {
    public MultipleChoice(String question, String[] answers, int correct) {
        this.question = question;
        this.answers = answers;
        this.correct = correct;
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
    public void use(Vis v) {
        v.on(this);
    }
    private String question;
    private String[] answers;
    private int correct;
}