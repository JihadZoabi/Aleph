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
        /* final LockThatLocks l = new LockThatLocks();
        l.lock(); */
        Log.d("NewTitle", "really?");
        t = new TextToSpeech(c, s -> {
            if (s != TextToSpeech.SUCCESS)
                Log.d("INIT", "What's happening");
            Log.d("INIT", "I am not running");
            t.setLanguage(Locale.forLanguageTag("he-IL"));
            //l.unlock();
            t.speak("אייל זה עובד", TextToSpeech.QUEUE_FLUSH, null, null);
        });
        // Stop

        Log.d("NewTitle", "this can't be right ");
        /*try { l.acquire(); } catch (Exception e) {
            Log.d("NewTitle", "Seriously");
        }*/
        //try { Thread.sleep(10_000); }
        //catch (Exception e) {}
        Log.d("INIT", "reached");
        //l.lock();

        Log.d("NewTitle", "I am reached");

    }
    public void close() {
        t.shutdown();
    }
}
