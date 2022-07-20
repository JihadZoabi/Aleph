package com.example.prototype;

import java.util.HashMap;
import android.content.res.Resources;
import android.content.res.TypedArray;

public class Lessons {
    private HashMap<String, Lesson> lessons;
    private Lessons(Resources r) {
        lessons = new HashMap<>();
        TypedArray ls = r.obtainTypedArray(R.array.lessons);
        for (int i = 0; i < ls.length(); ++i) {
            TypedArray lesson = r.obtainTypedArray(ls.getResourceId(i, -1));
            switch (lesson.getString(0)) {
                case "CompleteSentence":
                    break;
                case "MultipleChoice":
                    break;
                default:
                    continue;
            }
        }
    }
    public Lesson getLesson(String name) {
        return lessons.get(name);
    }
    private static Lessons l = null;
    static void init(Resources res) {
        l = new Lessons(res);
    }
    static Lesson get(String name) {
        return get().getLesson(name);
    }
    static Lessons get() {
        return l;
    }
}