package com.example.prototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.prototype.fragments.CompleteSentenceFragment;
import com.example.prototype.fragments.MultipleChoiceFragment;

public class LessonActivity extends AppCompatActivity {
    Button continueBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        //init
        continueBtn = findViewById(R.id.continueBtn);

        /*lessonLength = getIntent().getExtras().getInt("length");
        lessonArr = new int[lessonLength];*/

        buildLesson(Lessons.get("lessonName"));
    }
    public void buildLesson(Lesson l){
        final int[] i = {0};
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i[0]++;
                if(i[0] < l.count()){
                    l.getQ(i[0]).use(new Visitor() {
                        public void on(MultipleChoice m) {

                        }
                        public void on(CompleteSentence c) {
                        }
                    });
                }
            }
        });
    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();

        mTransactiont.replace(R.id.questionContainer, fragment, fragment.getClass().getName());
        mTransactiont.commit();
    }
}