package com.example.prototype;

public class BadXML extends Exception {
    public BadXML(String c) {
        context = c;
    }
    public String toString() {
        return context;
    }
    private String context;
}
