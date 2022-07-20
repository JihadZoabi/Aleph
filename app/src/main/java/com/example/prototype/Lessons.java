package com.example.prototype;

import java.util.HashMap;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;

public class Lessons {
    // Used to be a singleton, maybe should be one for testing
    private static final HashMap<String, Lesson> lessons = new HashMap<>();
    private static TypedArray getArr(TypedArray arr, int index) {
        int id = arr.getResourceId(index, -1);
        if (id < 0)
            throw new BadXML();
        return r.obtainTypedArray(id);
    }
    private static String getString(TypedArray arr, int index) {
        if (index < 0 || index >= arr.length())
            throw new BadXML();
        return arr.getString(index);
    }
    public static Lesson get(String s) {
        return lessons.get(s);
    }
    private Lessons() {}
    public static void init() {
        if (r == null)
            throw new BadXML();
        TypedArray table = r.obtainTypedArray(R.array.lessons);
        for (int i = 0; i < table.length(); ++i) {
            TypedArray lesson = getArr(table, i);
            Question[] qs = new Question[lesson.length() - 1];
            String name = getString(lesson, 0);
            for (int j = 1; j < lesson.length(); ++j) {
                TypedArray q = getArr(lesson, i);
                String[] data = new String[q.length() - 1];
                for (int k = 1; k < q.length(); ++k)
                    data[k - 1] = q.getString(k);
                String type = getString(q, 0);
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
    static void init(Resources res) {
        r = res;
    }
    private static Resources r;
}