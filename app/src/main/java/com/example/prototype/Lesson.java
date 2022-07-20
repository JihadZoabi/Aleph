package com.example.prototype;

public class Lesson {
    Question[] qs;
    String name;
    int difficulty;
    public String getName() {
        return name;
    }
    public int getDifficulty() {
        return difficulty;
    }
    Question getQ(int index) {
        return qs[index];
    }
    public Lesson(String name, int difficulty, Question[] qs) {
        this.qs = qs;
        this.name = name;
        this.difficulty = difficulty;
    }
}
