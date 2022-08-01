package com.example.prototype;

import androidx.annotation.NonNull;

public class Lesson {
    private final Question[] qs;
    private final String name;
    private int xp = 0;
    private int correctAnswers = 0;

    public int getLength() {
        return qs.length;
    }

    public void reset() {
        xp = 0;
    }

    public int getXP() {
        return xp;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void gotCorrect(Question q) {
        xp += q.getXP();
        correctAnswers++;
    }

    @NonNull
    public String toString() {
        StringBuilder str = new StringBuilder(name + ":\n");
        for (int i = 0; i < qs.length; ++i) {
            str.append(qs[i]).append("\n");
        }
        return str.toString();
    }

    public String getName() {
        return name;
    }

    public int count() {
        return qs.length;
    }

    Question getQ(int index) {
        return qs[index];
    }

    public Lesson(String name, Question[] qs) {
        this.qs = qs;
        this.name = name;
    }
}
