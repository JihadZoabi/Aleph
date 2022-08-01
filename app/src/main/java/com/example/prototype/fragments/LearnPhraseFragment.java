package com.example.prototype.fragments;

import static android.speech.SpeechRecognizer.ERROR_AUDIO;
import static android.speech.SpeechRecognizer.ERROR_CLIENT;
import static android.speech.SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS;
import static android.speech.SpeechRecognizer.ERROR_LANGUAGE_NOT_SUPPORTED;
import static android.speech.SpeechRecognizer.ERROR_LANGUAGE_UNAVAILABLE;
import static android.speech.SpeechRecognizer.ERROR_NETWORK;
import static android.speech.SpeechRecognizer.ERROR_NETWORK_TIMEOUT;
import static android.speech.SpeechRecognizer.ERROR_NO_MATCH;
import static android.speech.SpeechRecognizer.ERROR_SERVER;
import static android.speech.SpeechRecognizer.ERROR_TOO_MANY_REQUESTS;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.prototype.BadXML;
import com.example.prototype.LearnPhrase;
import com.example.prototype.Lesson;
import com.example.prototype.LessonActivity;
import com.example.prototype.MultipleChoice;
import com.example.prototype.R;
import com.example.prototype.TextToAzure;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class LearnPhraseFragment extends Fragment {
    private TextView meaningInArabic;
    private ImageView pictureOfWord;
    private TextView HebrewWordInArabic;
    private TextView WordInHebrew;
    private View micButton;
    private final Integer RecordAudioRequestCode = 1;
    private SpeechRecognizer speechRecognizer;
    private final LearnPhrase lp;
    private final Lesson l;
    private ImageView soundVoice;
    private TextToAzure tta;

    public LearnPhraseFragment(Lesson l, LearnPhrase lp){
        this.l = l;
        l.reset();
        this.lp = lp;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_learn_phrase, container, false);
        meaningInArabic = (TextView) v.findViewById(R.id.meaningOfHebrewWordInArabic);
        pictureOfWord = (ImageView) v.findViewById(R.id.pictureOfWord);
        HebrewWordInArabic = (TextView) v.findViewById(R.id.HebrewWordInArabic);
        WordInHebrew = (TextView) v.findViewById(R.id.WordInHebrew);
        micButton = v.findViewById(R.id.micButton);
        soundVoice = (ImageView) v.findViewById(R.id.sound_wave);
        tta = new TextToAzure(getResources());

        l.gotCorrect(lp); // Always correct.

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());

        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "he-IL");

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getActivity(), "NO PERMISSIONS", Toast.LENGTH_SHORT).show();
        }

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                HebrewWordInArabic.setText("Listening...");
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
                HebrewWordInArabic.setText(data.get(0));
                if (data.get(0).equals(lp.getAnswers()[2])) {
                    Toast.makeText(getActivity(), "CORRECT", Toast.LENGTH_SHORT).show();
                }
                else {
                    speechRecognizer.destroy();
                    Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
                }
                Log.d("SST",data.get(0));
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
                micButton.setBackgroundResource(R.drawable.rec_red);
                Log.d("SST","Hi");
                if (motionEvent.getAction() == MotionEvent.ACTION_BUTTON_RELEASE){
                    Log.d("checkmic", "works!");
                    micButton.setBackgroundResource(R.drawable.rec_def);
                    speechRecognizer.stopListening();
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
                return false;
            }
        });

        soundVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tta.speak(lp.getAnswers()[2]);
            }
        });


        updateQuestion();
        return v;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }

    private void updateQuestion() {
        meaningInArabic.setText(lp.getQuestion().toString());
        Picasso.get().load(lp.getAnswers()[0]).into(pictureOfWord);
        HebrewWordInArabic.setText(lp.getAnswers()[1]);
        WordInHebrew.setText(lp.getAnswers()[2]);
    }
}

