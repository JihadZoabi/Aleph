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
        questionArray = new int[]{0,1,0,1,0};

        /*lessonLength = getIntent().getExtras().getInt("length");
        lessonArr = new int[lessonLength];*/

        buildLesson();
    }

    public void buildLesson(){
        final int[] i = {0};
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i[0]++;
                if(i[0] < questionArray.length){
                    switch(questionArray[i[0]]){
                        case 0:
                            showFragment(new MultipleChoiceFragment());
                            break;
                        case 1:
                            showFragment(new CompleteSentenceFragment());
                            break;
                    }
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