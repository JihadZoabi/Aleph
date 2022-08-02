package com.example.prototype.fragments;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.prototype.BadXML;
import com.example.prototype.Lesson;
import com.example.prototype.LessonActivity;
import com.example.prototype.R;
import com.example.prototype.SignQuestion;
import com.squareup.picasso.Picasso;

public class SignFragment extends Fragment implements View.OnClickListener {
    private TextView mQuestionView;
    private ImageView mSignImage;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private String mAnswer;
    private Vibrator mVibrator;
    private final SignQuestion s;
    private final Lesson l;
    private MediaPlayer mp;
    private int flag = 0;

    public SignFragment(Lesson l, SignQuestion s){
        this.l = l;
        l.reset();
        this.s = s;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_sign, container, false);
        mQuestionView = (TextView) v.findViewById(R.id.sign_question);
        mSignImage = (ImageView) v.findViewById(R.id.signImage);
        mButtonChoice1 = (Button) v.findViewById(R.id.choice1);
        mButtonChoice2 = (Button) v.findViewById(R.id.choice2);
        mButtonChoice3 = (Button) v.findViewById(R.id.choice3);
        mVibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        mp = MediaPlayer.create(getActivity(), R.raw.correct_audio);

        mButtonChoice1.setOnClickListener(this);
        mButtonChoice2.setOnClickListener(this);
        mButtonChoice3.setOnClickListener(this);
        updateQuestion();
        LessonActivity.hideButton();
        return v;
    }

    private void updateQuestion() {
        mQuestionView.setText(s.getQuestion());
        Picasso.get().load(s.getBlurredImageLink()).into(mSignImage);
        mButtonChoice1.setText(s.getAnswers()[0]);
        mButtonChoice2.setText(s.getAnswers()[1]);
        mButtonChoice3.setText(s.getAnswers()[2]);
        mAnswer = s.getAnswers()[s.getCorrect()];
        switch (s.count()) {
            case 2:
                mButtonChoice3.setVisibility(View.INVISIBLE);
                break;
            case 3:
                mButtonChoice3.setText(s.getAnswers()[2]);
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
                Picasso.get().load(s.getImageLink()).into(mSignImage);
                mp.start();
                LessonActivity.revealButton();
                if (flag == 0) {
                    l.gotCorrect(s);
                    flag++;
                }
            } else {
                flag++;
                //TODO if wrong.
                Toast.makeText(getActivity(), "Incorrect!", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mVibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    mVibrator.vibrate(500);
                }
            }
        }
    }
}

