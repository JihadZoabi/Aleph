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
import com.example.prototype.fragments.FourPicturesFragment;
import com.example.prototype.fragments.MultipleChoiceFragment;

public class LessonActivity extends AppCompatActivity {
    static Button continueBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        continueBtn = findViewById(R.id.continueBtn);
        buildLesson(Lessons.get("lessonName"));
    }
    private void show(Question q) {
        q.use(new Visitor() {
            public void on(MultipleChoice m) {
                showFragment(new MultipleChoiceFragment(m));
            }
            public void on(CompleteSentence c) {
                showFragment(new CompleteSentenceFragment(c));
            }
            public void on(FourPictures f) {
                showFragment(new FourPicturesFragment(f));
            }
        });
    }
    public void buildLesson(Lesson l){
        final int[] i = {1};
        show(l.getQ(0));
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i[0] < l.count()){
                    show(l.getQ(i[0]));
                }
                i[0]++;
            }
        });
    }
    public void showFragment(Fragment fragment) {
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.questionContainer, fragment, fragment.getClass().getName());
        mTransaction.commit();
    }

    public static void revealButton() {continueBtn.setVisibility(View.VISIBLE);}
    public static void hideButton() {continueBtn.setVisibility(View.INVISIBLE);}
}