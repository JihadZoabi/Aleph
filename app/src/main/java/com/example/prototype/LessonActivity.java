package com.example.prototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.prototype.fragments.CompleteSentenceFragment;
import com.example.prototype.fragments.FourPicturesFragment;
import com.example.prototype.fragments.MultipleChoiceFragment;

public class LessonActivity extends AppCompatActivity {
    static Button continueBtn;
    private ProgressBar barButTheProgressBarNotThePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        continueBtn = findViewById(R.id.continueBtn);
        barButTheProgressBarNotThePerson = findViewById(R.id.lesson_progress);
        buildLesson(Lessons.get("lessonName"));
    }

    private void show(Lesson l, int i) {
        l.getQ(i).use(new Visitor() {
            public void on(MultipleChoice m) {
                showFragment(new MultipleChoiceFragment(l, m));
            }
            public void on(CompleteSentence c) {
                showFragment(new CompleteSentenceFragment(l, c));
            }
            public void on(FourPictures f) {
                showFragment(new FourPicturesFragment(l, f));
            }
        });
    }

    private static int percent(int a, int b) {
        return (int) ((double) a * 100 / b);
    }

    public void buildLesson(Lesson l){
        final int[] i = {1};
        show(l, i[0]);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i[0] < l.count()) {
                    int progressForBarTheProgressbarNotThePerson = percent(i[0], l.count());
                    Log.d("division", String.valueOf(progressForBarTheProgressbarNotThePerson));
                    barButTheProgressBarNotThePerson.setProgress(progressForBarTheProgressbarNotThePerson);
                    show(l, i[0]);
                }
                if (i[0] == l.count()) {
                    startActivity(new Intent(LessonActivity.this, LessonFinishActivity.class));
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

    public static void revealButton() {
        continueBtn.setVisibility(View.VISIBLE);
    }

    public static void hideButton() {
        continueBtn.setVisibility(View.INVISIBLE);
    }
}