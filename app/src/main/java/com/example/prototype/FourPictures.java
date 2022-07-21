package com.example.prototype;

import java.util.Arrays;

public class FourPictures implements Question {
    public FourPictures(String question, String[] pictureLinks, int correct) {
        this.question = question;
        this.answers = pictureLinks;
        this.correct = correct;
    }
    public FourPictures(String[] data) {
        if (data.length != 3)
            throw new BadXML();
        this.question = data[0];
        this.correct = Integer.parseInt(data[1]);
        this.answers = Arrays.copyOfRange(data, 2, data.length);
    }

    protected String stringer() {
        String str = question + " The correct answer is " + correct +
                ".\nThe answers are:";
        for (int i = 0; i < answers.length; ++i)
            str += "\n" + answers[i];
        return str;
    }

    public String toString() {
        return "FourPictures: " + stringer();
    }
    public int count() {
        return answers.length;
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