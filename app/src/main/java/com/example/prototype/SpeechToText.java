package com.example.prototype;

import static android.speech.SpeechRecognizer.*;
import static android.speech.SpeechRecognizer.createSpeechRecognizer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.TextView;

import com.example.prototype.fragments.HomeFragment;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class SpeechToText {
    private SpeechRecognizer s;
    private Intent i;
    private Consumer<String> consume;
    private TextView tv;
    public SpeechToText(Context c, TextView tv) {
        s = SpeechRecognizer.createSpeechRecognizer(c);
        s.setRecognitionListener(new RL());
        i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "he-IL");
        this.tv = tv;
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
                    Log.d("SST_ERROR","Requested language is not available to be used with the current recognizer.");
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
            List<String> data = res.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            consume.accept(data.get(0));
            tv.setText(data.get(0));
            s.stopListening();
        }
    }
}