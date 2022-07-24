package com.example.prototype;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import java.util.List;
import java.util.Locale;

public class SpeechToText {
    private SpeechRecognizer s;
    private Intent i;
    public SpeechToText(Context c) {
        s = SpeechRecognizer.createSpeechRecognizer(c);
        s.setRecognitionListener(new RL());
        i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.forLanguageTag("he"));
    }
    public void start() {
        s.startListening(i);
        final Handler h = new Handler();
        Log.d("STT", "start");
    }
    public void destroy() {
        s.destroy();
    }
    private class RL implements RecognitionListener {
        public void onBeginningOfSpeech() {}
        public void onBufferReceived(byte[] buffer) {}
        public void onEndOfSpeech() {}
        public void onError(int error) {}
        public void onEvent(int eventType, Bundle params) {}
        public void onPartialResults(Bundle partialResults) {}
        public void onReadyForSpeech(Bundle params) {
            Log.d("STT", "ready");
        }
        public void onRmsChanged(float rmsdB) {}
        public void onResults(Bundle res) {
            List<String> data =
                    res.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            Log.d("STT", data.get(0));
        }
    }
}