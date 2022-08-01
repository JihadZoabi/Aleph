package com.example.prototype;

import android.content.res.Resources;
import android.util.Log;

import com.microsoft.cognitiveservices.speech.CancellationReason;
import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisCancellationDetails;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisResult;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;

public class TextToAzure {
    private final SpeechConfig config;
    private final SpeechSynthesizer synth;
    public TextToAzure(Resources r) {
        config = SpeechConfig.fromSubscription(r.getString(R.string.key), r.getString(R.string.area));
        config.setSpeechSynthesisVoiceName("he-IL-AvriNeural");
        synth = new SpeechSynthesizer(config);
    }
    public void speak(String text) {
        try {
            SpeechSynthesisResult speechRecognitionResult = synth.SpeakTextAsync(text).get();
            if (speechRecognitionResult.getReason() == ResultReason.SynthesizingAudioCompleted) {
                System.out.println("Speech synthesized to speaker for text [" + text + "]");
            } else if (speechRecognitionResult.getReason() == ResultReason.Canceled) {
                SpeechSynthesisCancellationDetails cancellation = SpeechSynthesisCancellationDetails.fromResult(speechRecognitionResult);
                System.out.println("CANCELED: Reason=" + cancellation.getReason());

                if (cancellation.getReason() == CancellationReason.Error) {
                    System.out.println("CANCELED: ErrorCode=" + cancellation.getErrorCode());
                    System.out.println("CANCELED: ErrorDetails=" + cancellation.getErrorDetails());
                    System.out.println("CANCELED: Did you set the speech resource key and region values?");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}