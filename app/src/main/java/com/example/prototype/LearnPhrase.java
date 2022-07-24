package com.example.prototype;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class LearnPhrase implements Question {
    public LearnPhrase(String question, String[] answers, int correct, int xp) {
        this.question = question;
        this.answers = answers;
        this.correct = correct;
        this.xp = xp;
    }

    public LearnPhrase(String[] data) {
        if (data.length < 4)
            throw new BadXML();
        question = data[0];
        xp = Integer.parseInt(data[1]);
        correct = Integer.parseInt(data[2]);
        if (correct > data.length - 3) {
            // Log.d("Jihad's bad code",correct + " " + (data.length-3));
            throw new BadXML();
        }
        answers = Arrays.copyOfRange(data, 3, data.length);
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
        return "MultipleChoice: " + stringer();
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

    public int getXP() {
        return xp;
    }

    private final int xp;
    private final String question;
    private final String[] answers;
    private final int correct;
}