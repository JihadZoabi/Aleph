package com.example.prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.prototype.fragments.CompleteSentenceFragment;
import com.example.prototype.fragments.MultipleChoiceFragment;

public class LessonActivity extends AppCompatActivity {


    private int[] questionArray;
    private int lessonLength = 3;

    Button continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        //init
        continueBtn = findViewById(R.id.continueBtn);

        //This is just for testing purposes
        questionArray = new int[]{0,0,0};

        /*lessonLength = getIntent().getExtras().getInt("length");
        lessonArr = new int[lessonLength];*/

        buildLesson();
    }

    public void buildLesson(){
        final int[] i = {0};
        final boolean[] flag = {true};
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i[0]++;
                flag[0] = true;
                if (i[0] >= questionArray.length) {
                }
            }
        });
        while (i[0] < questionArray.length){
            //Check which fragment is supposed to load
            if (flag[0]) {
                switch (0) {
                    case 0:
                        MultipleChoiceFragment f = new MultipleChoiceFragment();
                        getSupportFragmentManager().beginTransaction().add(R.id.questionContainer, f).commit();
                        break;
                }
            }
            flag[0] = false;
        }
        startActivity(new Intent(LessonActivity.this, MainActivity.class));
    }
}