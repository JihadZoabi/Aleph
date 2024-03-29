package com.example.prototype.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;


import androidx.fragment.app.Fragment;

import com.example.prototype.BadXML;
import com.example.prototype.Lesson;
import com.example.prototype.LessonActivity;
import com.example.prototype.MultipleChoice;
import com.example.prototype.R;
import com.example.prototype.WrongDialog;

public class MultipleChoiceFragment extends Fragment implements View.OnClickListener {
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private String mAnswer;
    private Vibrator mVibrator;
    private final MultipleChoice m;
    private final Lesson l;
    private MediaPlayer mp;
    private int flag = 0;

    public MultipleChoiceFragment(Lesson l, MultipleChoice m){
        this.l = l;
        l.reset();
        this.m = m;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_multiple_choice, container, false);
        mQuestionView = (TextView) v.findViewById(R.id.question);
        mButtonChoice1 = (Button) v.findViewById(R.id.choice1);
        mButtonChoice2 = (Button) v.findViewById(R.id.choice2);
        mButtonChoice3 = (Button) v.findViewById(R.id.choice3);
        mButtonChoice4 = (Button) v.findViewById(R.id.choice4);
        mVibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        mp = MediaPlayer.create(getActivity(), R.raw.correct_audio);

        mButtonChoice1.setOnClickListener(this);
        mButtonChoice2.setOnClickListener(this);
        mButtonChoice3.setOnClickListener(this);
        mButtonChoice4.setOnClickListener(this);
        updateQuestion();
        LessonActivity.hideButton();
        return v;
    }

    private void updateQuestion() {
        mQuestionView.setText(m.getQuestion());
        mButtonChoice1.setText(m.getAnswers()[0]);
        mButtonChoice2.setText(m.getAnswers()[1]);
        mAnswer = m.getAnswers()[m.getCorrect()];
        switch (m.count()) {
            case 2:
                mButtonChoice3.setVisibility(View.INVISIBLE);
                mButtonChoice4.setVisibility(View.INVISIBLE);
                break;
            case 3:
                mButtonChoice3.setText(m.getAnswers()[2]);
                mButtonChoice4.setVisibility(View.INVISIBLE);
                break;
            case 4:
                mButtonChoice3.setText(m.getAnswers()[2]);
                mButtonChoice4.setText(m.getAnswers()[3]);
                break;
            default:
                throw new BadXML();
        }

    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            Button chosenButton = (Button) v;
            if (chosenButton.getText().toString().equals(mAnswer)) {
                //TODO if correct.
                Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
                LessonActivity.revealButton();
                mp.start();
                if (flag == 0) {
                    flag++;
                    l.gotCorrect(m);
                    Log.d("CHECK","CORRECT " + flag);
                }
            } else {
                //TODO if wrong.
                WrongDialog wrongDialog = new WrongDialog();
                wrongDialog.show(getActivity().getSupportFragmentManager(), "wrong");
                flag++;
                Log.d("CHECK","FALSE" + flag);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.d("JIHAD'S SHITTY CODE","HERE");
                    mVibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    Log.d("JIHAD'S SHITTY CODE","HERE2");
                    mVibrator.vibrate(500);
                }
            }
        }
    }
}

