package com.example.prototype;

import androidx.annotation.NonNull;

public class Lesson {
    private final Question[] qs;
    private final String name;

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
