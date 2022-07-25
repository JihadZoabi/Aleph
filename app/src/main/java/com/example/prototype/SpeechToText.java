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
import java.util.function.Consumer;

public class SpeechToText {
    private SpeechRecognizer s;
    private Intent i;
    private Consumer<String> consume;
    public SpeechToText(Context c) {
        s = SpeechRecognizer.createSpeechRecognizer(c);
        s.setRecognitionListener(new RL());
        i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "he-IL" /* Locale.forLanguageTag("es") */);
    }
    public void start(Consumer<String> c) {
        s.startListening(i);
        consume = c;
        final Handler h = new Handler();
        Log.d("STT", "start");
    }
    public void destroy() {
        s.destroy();
    }
    private class RL implements RecognitionListener {
        public void onBeginningOfSpeech() {
            /* Message to the user to begin. */
        }
        public void onBufferReceived(byte[] buffer) {
            /* DO NOT TOUCH */
        }
        public void onEndOfSpeech() {
            /* IDK what's the relationship of this
               to other methods temporally.
            */
        }
        public void onError(int error) {
            Log.d("STT", "Error: " + error);
        }
        public void onEvent(int eventType, Bundle params) {}
        public void onPartialResults(Bundle partialResults) {
            /* Maybe figure out when this happens.
               If we only want one word maybe this has value.
             */
        }
        public void onReadyForSpeech(Bundle params) {
            Log.d("STT", "ready");
        }
        public void onRmsChanged(float rmsdB) {
            /* Uninteresting */
        }
        public void onResults(Bundle res) {
            List<String> data =
                    res.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            consume.accept(data.get(0));
        }
    }
}