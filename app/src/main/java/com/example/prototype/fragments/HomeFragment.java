package com.example.prototype.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import static android.speech.SpeechRecognizer.*;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prototype.FoodActivity;
import com.example.prototype.LessonActivity;
import com.example.prototype.R;
import com.example.prototype.SpeechToText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private View sportRect;
    private View foodRect;
    private View schoolRect;
    private final Integer RecordAudioRequestCode = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        sportRect = v.findViewById(R.id.sportRect);
        foodRect = v.findViewById(R.id.foodRect);
        schoolRect = v.findViewById(R.id.SchoolRect);


        sportRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LessonActivity.class));
            }
        });

        foodRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FoodActivity.class));
            }
        });

        schoolRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "BOO", Toast.LENGTH_SHORT).show();
            }
        });

        if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getActivity(), "NO PERMISSIONS", Toast.LENGTH_SHORT).show();
            checkPermission();
        }


        return v;
    }


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }
}