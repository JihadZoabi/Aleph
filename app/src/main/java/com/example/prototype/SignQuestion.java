package com.example.prototype;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class SignQuestion implements Question {
    public SignQuestion(String question, String blurredImageLink, String imageLink, String[] answers, int correct, int xp) {
        this.question = question;
        this.blurredImageLink = blurredImageLink;
        this.imageLink = imageLink;
        this.answers = answers;
        this.correct = correct;
        this.xp = xp;
    }

    public SignQuestion(String[] data) {
        if (data.length < 4)
            throw new BadXML();
        question = data[0];
        imageLink = data[1];
        blurredImageLink = data[2];
        xp = Integer.parseInt(data[3]);
        correct = Integer.parseInt(data[4]);
        if (correct > data.length - 3)
            throw new BadXML();
        answers = Arrays.copyOfRange(data, 5, data.length);
    }

    protected String stringer() {
        StringBuilder str = new StringBuilder(question +
                " The correct answer is " + correct +
                ".\nThe answers are:");
        for (int i = 0; i < answers.length; ++i)
            str.append("\n").append(answers[i]);
        return str.toString();
    }

    @NonNull
    public String toString() {
        return "SignQuestion: " + stringer();
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

    public String getImageLink() {return imageLink;}

    public String getBlurredImageLink() {return blurredImageLink;}

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
    private final String imageLink;
    private final String blurredImageLink;
}