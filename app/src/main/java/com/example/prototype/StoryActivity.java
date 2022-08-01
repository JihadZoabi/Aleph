package com.example.prototype;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StoryActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] messages;
    private String[] translations;
    private RelativeLayout[] layouts;
    private Button revealNext;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        revealNext = findViewById(R.id.next);
        linearLayout = findViewById(R.id.linear);

        Bundle bundle = getIntent().getExtras();

        messages = new String[bundle.getStringArrayList("messages").size()];
        messages = bundle.getStringArrayList("messages").toArray(messages);

        translations = new String[bundle.getStringArrayList("translations").size()];
        translations = bundle.getStringArrayList("translations").toArray(translations);

        layouts = new RelativeLayout[100];

        layouts[0] = findViewById(R.id.dialog1);
        layouts[1] = findViewById(R.id.dialog2);
        layouts[2] = findViewById(R.id.dialog3);
        layouts[3] = findViewById(R.id.dialog4);
        layouts[4] = findViewById(R.id.dialog5);
        layouts[5] = findViewById(R.id.dialog6);
        layouts[6] = findViewById(R.id.dialog7);
        layouts[7] = findViewById(R.id.dialog8);


        for(int i = 0; i < messages.length; i++){
            ((TextView) layouts[i].getChildAt(0)).setText(messages[i]);
            layouts[i].setOnClickListener(this);
        }

        final int[] counter = {0};
        revealNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter[0] < messages.length){
                    layouts[counter[0]].setVisibility(View.VISIBLE);
                    counter[0]++;
                }
                else {
                    Toast.makeText(StoryActivity.this, "Story finished", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view instanceof RelativeLayout){
            String text = translations[linearLayout.indexOfChild(view)];
            TranslationDialog translationDialog = new TranslationDialog(text);
            translationDialog.show(getSupportFragmentManager(), "translate!");
        }
    }
}