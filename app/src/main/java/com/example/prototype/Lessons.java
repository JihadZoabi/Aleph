package com.example.prototype;

import java.util.HashMap;
import android.content.res.Resources;
import android.content.res.TypedArray;

public class Lessons {
    private HashMap<String, Lesson> lessons;
    private Lessons(Resources r) {
        lessons = new HashMap<>();
        TypedArray table = r.obtainTypedArray(R.array.lessons);
        Lesson[] ls = new Lesson[table.length()];
        for (int i = 0; i < table.length(); ++i) {
            TypedArray lesson = r.obtainTypedArray(table.getResourceId(i, -1));
            Question[] qs = new Question[lesson.length() - 1];
            String name = lesson.getString(0);
            for (int j = 0; j < lesson.length(); ++j) {
                TypedArray q = r.obtainTypedArray(lesson.getResourceId(j, -1));
                String[] data = new String[q.length() - 1];
                for (int k = 1; k < q.length(); ++k)
                    data[k] = q.getString(k);
                String type = q.getString(0);
                switch (type) {
                    case "CompleteSentence":
                        qs[j] = new CompleteSentence(data);
                        break;
                    case "MultipleChoice":
                        qs[j] = new MultipleChoice(data);
                        break;
                    default:
                        continue;
                }
            }
            lessons.put(name, new Lesson(name, qs));
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