package com.example.prototype;

import java.util.Arrays;

public class FourPictures implements Question {
    public FourPictures(String question, String[] pictureLinks, int correct, int xp) {
        this.question = question;
        this.answers = pictureLinks;
        this.correct = correct;
        this.xp = xp;
    }

    public FourPictures(String[] data) {
        if (data.length != 7)
            throw new BadXML();
        question = data[0];
        correct = Integer.parseInt(data[1]);
        xp = Integer.parseInt(data[2]);
        answers = Arrays.copyOfRange(data, 3, data.length);
    }

    protected String stringer() {
        StringBuilder str = new StringBuilder(question +
                " The correct answer is " + correct +
                ".\nThe answers are:");
        for (int i = 0; i < answers.length; ++i) {
            str.append("\n").append(answers[i]);
        }
        return str.toString();
    }

    public String toString() {
        return "FourPictures: " + stringer();
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

    public int getXP() {
        return xp;
    }

    private final int xp;
    private final String question;
    private final String[] answers;
    private final int correct;
}