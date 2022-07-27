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
    private Button micButton;
    private TextView resultsTextView;
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
        micButton = v.findViewById(R.id.speak_button);
        resultsTextView = v.findViewById(R.id.speak_results);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());


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

        if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getActivity(), "NO PERMISSIONS", Toast.LENGTH_SHORT).show();
            checkPermission();
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                resultsTextView.setText("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {
                // Irrelevant
            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {
                resultsTextView.setText("Listen");
                Log.d("SST","VOICE ENDED");
            }

            @Override
            public void onError(int error) {
                switch (error) {
                    case ERROR_AUDIO:
                        Log.d("SST_ERROR","Audio recording error.");
                        break;
                    case 14:
                        //ERROR_CANNOT_CHECK_SUPPORT
                        Log.d("SST_ERROR","The service does not allow to check for support.");
                        break;
                    case ERROR_CLIENT:
                        Log.d("SST_ERROR","Other client side errors.");
                        break;
                    case ERROR_INSUFFICIENT_PERMISSIONS:
                        Log.d("SST_ERROR","Insufficient permissions");
                        break;
                    case ERROR_LANGUAGE_NOT_SUPPORTED:
                        Log.d("SST_ERROR","Requested language is not available to be used with the current version");
                        break;
                    case ERROR_LANGUAGE_UNAVAILABLE:
                        Log.d("SST_ERROR","Requested language is supported, but not available currently");
                        break;
                    case ERROR_NETWORK:
                        Log.d("SST_ERROR","Other network related errors.");
                        break;
                    case ERROR_TOO_MANY_REQUESTS:
                        Log.d("SST_ERROR","Too many requests from the same client.");
                        break;
                    case ERROR_SERVER:
                        Log.d("SST_ERROR","Server sends error status.");
                        break;
                    case ERROR_NO_MATCH:
                        Log.d("SST_ERROR","No recognition result matched.");
                        break;
                    case ERROR_NETWORK_TIMEOUT:
                        Log.d("SST_ERROR","Network operation timed out.");
                        break;
                }
            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                resultsTextView.setText(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        micButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    speechRecognizer.stopListening();
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });
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