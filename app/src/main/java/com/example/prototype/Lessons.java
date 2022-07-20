package com.example.prototype;

import java.util.HashMap;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;

public class Lessons {
    private final HashMap<String, Lesson> lessons;
    private TypedArray find(TypedArray arr, int index) {
        int id = arr.getResourceId(index, -1);
        if (id < 0)
            throw new BadXML("f")
    }
    private Lessons(Resources res) {
        r = res;
        lessons = new HashMap<>();
        TypedArray table = r.obtainTypedArray(R.array.lessons);
        for (int i = 0; i < table.length(); ++i) {
            TypedArray lesson = r.obtainTypedArray(table.getResourceId(i, -1));
            Question[] qs = new Question[lesson.length() - 1];
            String name = lesson.getString(0);
            Log.d("Bar", name);
            for (int j = 1; j < lesson.length(); ++j) {
                TypedArray q = r.obtainTypedArray(lesson.getResourceId(j, -1));
                String[] data = new String[q.length() - 1];
                for (int k = 1; k < q.length(); ++k)
                    data[k - 1] = q.getString(k);
                String type = q.getString(0);
                switch (type) {
                    case "CompleteSentence":
                        qs[j - 1] = new CompleteSentence(data);
                        break;
                    case "MultipleChoice":
                        qs[j - 1] = new MultipleChoice(data);
                        break;
                    default:
                        break;
                }
            }
            lessons.put(name, new Lesson(name, qs));
        }
    }
    public Lesson getLesson(String name) {
        return lessons.get(name);
    }
    static void init(Resources res) {
        l = new Lessons(res);
    }
    static Lesson get(String name) {
        return l.getLesson(name);
    }
    static Lessons get() {
        return l;
    }
    private Resources r;
    private static Lessons l = null;
}