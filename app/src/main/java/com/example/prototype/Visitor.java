package com.example.prototype;

public interface Visitor {
    void on(MultipleChoice m);
    void on(CompleteSentence c);
    void on(FourPictures f);
    void on(LearnPhrase l);
    void on(SignQuestion s);
}