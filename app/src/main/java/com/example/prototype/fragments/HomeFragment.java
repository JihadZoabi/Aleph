package com.example.prototype.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prototype.FoodActivity;
import com.example.prototype.LessonActivity;
import com.example.prototype.R;

public class HomeFragment extends Fragment {

    View rect6;
    View foodRect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        rect6 = v.findViewById(R.id.rectangle_6);
        foodRect = v.findViewById(R.id.rectangle_7);

        rect6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LessonActivity.class));
            }
        });

        foodRect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FoodActivity.class));
            }
        });



        return v;
    }
}

/*
Lessons.get({name_of_lesson: String});
 */