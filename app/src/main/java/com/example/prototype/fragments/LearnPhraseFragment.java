package com.example.prototype.fragments;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.prototype.BadXML;
import com.example.prototype.LearnPhrase;
import com.example.prototype.Lesson;
import com.example.prototype.LessonActivity;
import com.example.prototype.MultipleChoice;
import com.example.prototype.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class LearnPhraseFragment extends Fragment {
    private TextView meaningInArabic;
    private ImageView pictureOfWord;
    private TextView HebrewWordInArabic;
    private TextView WordInHebrew;
    private final LearnPhrase lp;
    private final Lesson l;

    public LearnPhraseFragment(Lesson l, LearnPhrase lp){
        this.l = l;
        l.reset();
        this.lp = lp;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_learn_phrase, container, false);
        meaningInArabic = (TextView) v.findViewById(R.id.meaningOfHebrewWordInArabic);
        pictureOfWord = (ImageView) v.findViewById(R.id.pictureOfWord);
        HebrewWordInArabic = (TextView) v.findViewById(R.id.HebrewWordInArabic);
        WordInHebrew = (TextView) v.findViewById(R.id.WordInHebrew);
        updateQuestion();
        return v;
    }

    private void updateQuestion() {
        meaningInArabic.setText(lp.getQuestion().toString());
        Picasso.get().load(lp.getAnswers()[0]).into(pictureOfWord);
        HebrewWordInArabic.setText(lp.getAnswers()[1]);
        WordInHebrew.setText(lp.getAnswers()[2]);
    }
}

