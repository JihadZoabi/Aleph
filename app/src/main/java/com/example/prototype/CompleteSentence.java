package com.example.prototype;

public class CompleteSentence extends MultipleChoice implements Question {
    public CompleteSentence(String question, String[] answers, int correct) {
        super(question, answers, correct);
    }
    public CompleteSentence(String[] data) {
        super(data);
    }
    public void use(Visitor v) {
        v.on(this);
    }
}
