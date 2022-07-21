package com.example.prototype.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.prototype.CompleteSentence;
import com.example.prototype.FourPictures;
import com.example.prototype.R;

public class FourPicturesFragment extends Fragment {

    TextView questionText;
    EditText completeAnswer;

    private FourPictures f;

    public FourPicturesFragment(FourPictures f){
        this.f = f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_complete_sentence, container, false);

        questionText = v.findViewById(R.id.questionCompleteTheSentence);
        completeAnswer = v.findViewById(R.id.editTextAnswerCompleteQuestion);

        return v;
    }
}
