package com.example.prototype;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.EditText;

import java.util.Locale;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TextToSound {
    private TextToSpeech t;
    private EditText write;
    public TextToSound(Context c) {
        //final Semaphore l = new Semaphore(0);
        Log.d("NewTitle", "really?");
        t = new TextToSpeech(c, status -> {
            if (status == TextToSpeech.SUCCESS) {
                Log.d("NewTitle", "Nice");
                t.setLanguage(Locale.forLanguageTag("en-US"));
                //l.release();
            } else {
                Log.d("NewTitle", "We're fucked");
            }
        });
        Log.d("NewTitle", "this can't be right ");
        /*try { l.acquire(); } catch (Exception e) {
            Log.d("NewTitle", "Seriously");
        }*/
        try { Thread.sleep(3_000); }
        catch (Exception e) {}
        Log.d("NewTitle", "I am reached");
        t.speak("Jihad we're not moving", TextToSpeech.QUEUE_ADD, null, null);
    }
    public void close() {
        t.shutdown();
    }
}
