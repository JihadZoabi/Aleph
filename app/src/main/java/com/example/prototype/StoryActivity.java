package com.example.prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    private String[] messages;
    private RelativeLayout[] layouts;
    private Button revealNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        revealNext = findViewById(R.id.next);


        for(int i = 0; i < messages.length; i++){
            ((TextView) layouts[i].getChildAt(0)).setText(messages[i]);
        }

        int counter = 0;
        revealNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter <= messages.length){
                    layouts[counter].setVisibility(View.VISIBLE);
                }
                else {
                    //TODO: Move on to finish
                }
            }
        });
    }
}