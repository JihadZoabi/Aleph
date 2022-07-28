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
import com.example.prototype.User;

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
    private User user;

    public HomeFragment(User u) {
        user = u;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        sportRect = v.findViewById(R.id.sportRect);
        foodRect = v.findViewById(R.id.foodRect);
        schoolRect = v.findViewById(R.id.SchoolRect);
        transportRect = v.findViewById(R.id.TransportRect);

        Intent i = new Intent(getActivity(), LessonActivity.class);
        Bundle bundle = new Bundle();
        i.putExtra("user", user);

        class Handler implements View.OnClickListener {
            private String name;
            public Handler(String n) {
                name = n;
            }
            public void onClick(View v) {
                bundle.putString("LessonName", name);
                i.putExtras(bundle);
                startActivity(i);
            }
        }

        sportRect.setOnClickListener(new Handler("Sport"));
        foodRect.setOnClickListener(new Handler("Food"));
        schoolRect.setOnClickListener(new Handler("Greetings"));
        transportRect.setOnClickListener(new Handler("Transport"));

        if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getActivity(), "NO PERMISSIONS", Toast.LENGTH_SHORT).show();
            checkPermission();
        }

        return v;
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[] { Manifest.permission.RECORD_AUDIO }, RecordAudioRequestCode);
        }
    }
}