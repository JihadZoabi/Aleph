package com.example.prototype;

public class CompleteSentence implements Question {
    public String getSentence() {
        return sentence;
    }
    public String getCompletion() {
        return completion;
    }
    public CompleteSentence(String sentence, String completion) {
        this.sentence = sentence;
        this.completion = completion;
    }
    private String sentence;
    private String completion;
    public void use(Vis v) {
        v.on(this);
    }
}
