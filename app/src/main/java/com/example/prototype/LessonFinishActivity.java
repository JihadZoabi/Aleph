package com.example.prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LessonFinishActivity extends AppCompatActivity {
    private Button returnBtn;
    private TextView xp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_finish);
        returnBtn = findViewById(R.id.returnBtn);

        Bundle bundle = getIntent().getExtras();
        int xpNumber = bundle.getInt("xp");
        xp = findViewById(R.id.xp);
        xp.setText("+"+xpNumber);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LessonFinishActivity.this, MainActivity.class));
            }
        });
    }
}