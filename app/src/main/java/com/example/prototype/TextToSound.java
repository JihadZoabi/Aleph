package com.example.prototype;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.EditText;

import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TextToSound {
    private TextToSpeech t;
    private EditText write;
    public TextToSound(Context c) {
        Lock l = new ReentrantLock();
        Log.d("OurTTS", "I am reached first");
        l.lock();
        t = new TextToSpeech(c, status -> l.unlock());
        l.lock();
        Log.d("OurTTS", "I am reached");
        t.setLanguage(Locale.forLanguageTag("he-IL"));
        t.speak("Jihad we're not moving", TextToSpeech.QUEUE_FLUSH, null, null);
    }
    public void close() {
        t.shutdown();
    }
}
