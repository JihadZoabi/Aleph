package com.example.prototype.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.prototype.CompleteSentence;
import com.example.prototype.FourPictures;
import com.example.prototype.R;

public class FourPicturesFragment extends Fragment implements View.OnClickListener {

    private TextView questionText;
    private ImageView imageChoice1;
    private ImageView imageChoice2;
    private ImageView imageChoice3;
    private ImageView imageChoice4;

    private int correctImage;

    private FourPictures f;

    public FourPicturesFragment(FourPictures f){
        this.f = f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_four_pictures, container, false);

        questionText = v.findViewById(R.id.fourPicturesQuestion);
        imageChoice1 = v.findViewById(R.id.imageTopRight);
        imageChoice2 = v.findViewById(R.id.imageBottomRight);
        imageChoice3 = v.findViewById(R.id.imageBottomLeft);
        imageChoice4 = v.findViewById(R.id.imageTopLeft);

        return v;
    }

    public void updateQuestion() {
        questionText.setText(f.getQuestion());

    }

    @Override
    public void onClick(View view) {
        if (view instanceof ImageView) {
            ImageView mImage = (ImageView) view;

        }
    }
}
