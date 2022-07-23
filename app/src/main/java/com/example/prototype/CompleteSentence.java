package com.example.prototype;

import androidx.annotation.NonNull;

public class CompleteSentence extends MultipleChoice implements Question {
    public CompleteSentence(String question, String[] answers, int correct, int xp) {
        super(question, answers, correct, xp);
    }

    @NonNull
    public String toString() {
        return "CompleteSentence: " + stringer();
    }

    public CompleteSentence(String[] data) {
        super(data);
    }

    public void use(Visitor v) {
        v.on(this);
    }
}
