package com.example.prototype.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.prototype.CompleteSentence;
import com.example.prototype.FourPictures;
import com.example.prototype.Lesson;
import com.example.prototype.LessonActivity;
import com.example.prototype.R;
import com.squareup.picasso.Picasso;

public class FourPicturesFragment extends Fragment implements View.OnClickListener {
    private TextView questionText;
    private final ImageView[] imageChoices = new ImageView[4];
    private ImageView correct;
    private ImageView waveSound;
    private final FourPictures f;
    private final Lesson l;
    private Vibrator mVibrator;

    public FourPicturesFragment(Lesson l, FourPictures f){
        this.l = l;
        this.f = f;
        l.reset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_four_pictures, container, false);
        Picasso.get().setLoggingEnabled(true);
        questionText = v.findViewById(R.id.fourPicturesQuestion);
        mVibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        waveSound = v.findViewById(R.id.sound_wave);
        imageChoices[0] = (ImageView) v.findViewById(R.id.imageTopRight);
        imageChoices[1] = (ImageView) v.findViewById(R.id.imageBottomRight);
        imageChoices[2] = (ImageView) v.findViewById(R.id.imageBottomLeft);
        imageChoices[3] = (ImageView) v.findViewById(R.id.imageTopLeft);
        updateQuestion();
        LessonActivity.hideButton();
        for (int i = 0; i < 4; ++i) {
            imageChoices[i].setOnClickListener(this);
        }
        return v;
    }

    public void updateQuestion() {
        questionText.setText(f.getQuestion());
        for (int i = 0; i < 4; ++i) {
            Picasso.get().load(f.getAnswers()[i]).into(imageChoices[i]);
        }
        correct = imageChoices[f.getCorrect()];
    }

    @Override
    public void onClick(View view) {
        if (view instanceof ImageView) {
            ImageView chosenImage = (ImageView) view;
            if (chosenImage == correct) {
                Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
                LessonActivity.revealButton();
                l.gotCorrect(f);
            } else {
                Toast.makeText(getActivity(), "InCorrect!", Toast.LENGTH_SHORT).show();
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