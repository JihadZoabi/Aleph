package com.example.prototype;

import java.util.HashMap;
import android.content.res.Resources;
import android.content.res.TypedArray;

public class Lessons {
    private HashMap<String, Question[]> lessons;
    private Lessons(Resources r) {
        lessons = new HashMap<>();
        TypedArray qs = r.obtainTypedArray(R.array.lessons);
        for (int i = 0; i < qs.length(); ++i) {
            TypedArray lesson = r.obtainTypedArray(qs.getResourceId(i, -1));
            lesson.getString(0);
            for (int j = 0; j < lesson.length(); ++j) {
                int k = lesson.getResourceId(i, -1);
                if (k < 0) {
                    continue;
                }
            }
        }
    }
    public Question[] getLesson(String name) {
        return lessons.get(name);
    }
    private static Lessons l = null;
    static void init(Resources res) {
        l = new Lessons(res);
    }
    static Lessons get() {
        return l;
    }
}



