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
import com.example.prototype.StoryActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
    private View sportRect;
    private View foodRect;
    private View schoolRect;
    private View transportRect;
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
        transportRect = v.findViewById(R.id.TransportRect);


        sportRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LessonActivity.class));
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
                bundle.putString("LessonName","Food");
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        transportRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), StoryActivity.class);
                ArrayList<String> messages = new ArrayList<String>();
                ArrayList<String> translations = new ArrayList<String>();
                messages.add("Hello, what bus helps me to reach Tel Aviv from Jerusalem");
                messages.add("I think, it's line 480");
                messages.add("What hour would it arrive");
                messages.add("I think at 13:28");
                messages.add("Thank you very much!");
                translations.add("مرحبا، اي باص بوديني عتل ابيب من القدس؟");
                translations.add("بفكر انه باص رقم 480");
                translations.add("اي ساعة بيجي؟");
                translations.add("ممكن 13:28");
                translations.add("شكرا كثير");
                i.putStringArrayListExtra("messages", messages);
                i.putStringArrayListExtra("translations", translations);
                startActivity(i);
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