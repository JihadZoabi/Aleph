package com.example.prototype.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.prototype.BadXML;
import com.example.prototype.Lesson;
import com.example.prototype.LessonActivity;
import com.example.prototype.R;
import com.example.prototype.SignQuestion;

public class SignFragment extends Fragment implements View.OnClickListener {
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private String mAnswer;
    private Vibrator mVibrator;
    private final SignQuestion s;
    private final Lesson l;

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
        View v = inflater.inflate(R.layout.fragment_multiple_choice, container, false);
        mQuestionView = (TextView) v.findViewById(R.id.question);
        mButtonChoice1 = (Button) v.findViewById(R.id.choice1);
        mButtonChoice2 = (Button) v.findViewById(R.id.choice2);
        mButtonChoice3 = (Button) v.findViewById(R.id.choice3);
        mButtonChoice4 = (Button) v.findViewById(R.id.choice4);
        mVibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        mButtonChoice1.setOnClickListener(this);
        mButtonChoice2.setOnClickListener(this);
        mButtonChoice3.setOnClickListener(this);
        mButtonChoice4.setOnClickListener(this);
        updateQuestion();
        LessonActivity.hideButton();
        return v;
    }

    private void updateQuestion() {
        mQuestionView.setText(s.getQuestion());
        mButtonChoice1.setText(s.getAnswers()[0]);
        mButtonChoice2.setText(s.getAnswers()[1]);
        mAnswer = s.getAnswers()[s.getCorrect()];
        switch (s.count()) {
            case 2:
                mButtonChoice3.setVisibility(View.INVISIBLE);
                mButtonChoice4.setVisibility(View.INVISIBLE);
                break;
            case 3:
                mButtonChoice3.setText(s.getAnswers()[2]);
                mButtonChoice4.setVisibility(View.INVISIBLE);
                break;
            case 4:
                mButtonChoice3.setText(s.getAnswers()[2]);
                mButtonChoice4.setText(s.getAnswers()[3]);
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
                l.gotCorrect(s);
            } else {
                //TODO if wrong.
                Toast.makeText(getActivity(), "Incorrect!", Toast.LENGTH_SHORT).show();
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

