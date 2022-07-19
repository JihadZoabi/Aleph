package com.example.prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LessonActivity extends AppCompatActivity {


    private int[] lessonArr;
    private int lessonLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        lessonLength = getIntent().getExtras().getInt("length");
        lessonArr = new int[lessonLength];

        String[] arr = getResources().getStringArray(R.array.question1);

    }

}