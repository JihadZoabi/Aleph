package com.example.prototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.prototype.fragments.CompleteSentenceFragment;
import com.example.prototype.fragments.FourPicturesFragment;
import com.example.prototype.fragments.LearnPhraseFragment;
import com.example.prototype.fragments.MultipleChoiceFragment;

public class LessonActivity extends AppCompatActivity {
    private static Button continueBtn;
    private ProgressBar barButTheProgressBarNotThePerson;
    private Lesson l;
    private int curr;
    private SharedPreferences.Editor e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        continueBtn = findViewById(R.id.continueBtn);
        barButTheProgressBarNotThePerson = findViewById(R.id.lesson_progress);
        l = Lessons.get("lessonName");
        curr = 0;
        e = getSharedPreferences("history", Context.MODE_PRIVATE).edit();
        continueBtn.setOnClickListener(view -> advance());
        advance();
    }

    private static int percent(int a, int b) {
        return (int) ((double) a * 100 / b);
    }

    private void advance() {
        if (curr >= l.count()) {
            startActivity(new Intent(this, LessonFinishActivity.class));
            e.putInt(l.getName() + " done", 1);
            return;
        }
        int progressForBarTheProgressBarNotThePerson = percent(curr, l.count());
        barButTheProgressBarNotThePerson.setProgress(progressForBarTheProgressBarNotThePerson);
        l.getQ(curr++).use(new Visitor() {
            public void on(MultipleChoice m) {
                showFragment(new MultipleChoiceFragment(l, m));
            }

            public void on(CompleteSentence c) {
                showFragment(new CompleteSentenceFragment(l, c));
            }

            public void on(FourPictures f) {
                showFragment(new FourPicturesFragment(l, f));
            }

            public void on(LearnPhrase lp) {
                showFragment(new LearnPhraseFragment(l, lp));
            }
        });
        e.putInt(l.getName(), l.getXP());
        e.apply();
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