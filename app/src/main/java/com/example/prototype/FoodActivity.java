package com.example.prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class FoodActivity extends AppCompatActivity {

    private View blob1;
    private View blob2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_purple);
        blob1 = (View) findViewById(R.id.blob1);
        blob2 = (View) findViewById(R.id.blob2);

    }
}