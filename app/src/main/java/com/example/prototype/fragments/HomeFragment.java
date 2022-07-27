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
    private View transportRect;
    private final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;

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
        transportRect = v.findViewById(R.id.TransportRect);


        sportRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LessonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("LessonName","Sport");
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        foodRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LessonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("LessonName","Food");
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        schoolRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LessonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("LessonName","Greetings");
                i.putExtras(bundle);
                startActivity(i);
            }
        });


        transportRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LessonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("LessonName","Transport");
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getActivity(), "NO PERMISSIONS", Toast.LENGTH_SHORT).show();
            checkPermission();
        }
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }
}