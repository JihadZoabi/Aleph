package com.example.prototype;

public class Lesson {
    Question[] qs;
    String name;
    int difficulty;
    public String toString() {
        String str = name + ": " + difficulty + "\n";
        for (int i = 0; i < qs.length; ++i)
            str += qs[i] + "\n";
        return str;
    }
    public String getName() {
        return name;
    }
    public int getDifficulty() {
        return difficulty;
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
    public Lesson(String name, int difficulty, Question[] qs) {
        this.qs = qs;
        this.name = name;
        this.difficulty = difficulty;
    }
}
