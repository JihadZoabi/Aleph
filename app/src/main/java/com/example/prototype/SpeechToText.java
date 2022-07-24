package com.example.prototype;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;

import java.util.List;

public class SpeechToText {
    private SpeechRecognizer s;
    public SpeechToText(Context c) {
        s = SpeechRecognizer.createSpeechRecognizer(c);
        s.setRecognitionListener(new RL());
    }
    public void start() {
        s.startListening(new Intent());
        final Handler h = new Handler();
        Log.d("STT", "start");
        h.postDelayed(() -> {
            s.stopListening();
            Log.d("STT", "end");
        }, 5000);
    }
    private class RL implements RecognitionListener {
        public void onBeginningOfSpeech() {}
        public void onBufferReceived(byte[] buffer) {}
        public void onEndOfSpeech() {}
        public void onError(int error) {}
        public void onEvent(int eventType, Bundle params) {}
        public void onPartialResults(Bundle partialResults) {}
        public void onReadyForSpeech(Bundle params) {}
        public void onRmsChanged(float rmsdB) {}
        public void onResults(Bundle res) {
            List<String> data =
                    res.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            Log.d("STT", data.toString());
        }
    }
}