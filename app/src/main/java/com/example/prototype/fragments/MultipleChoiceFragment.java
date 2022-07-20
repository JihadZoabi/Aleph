package com.example.prototype.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.prototype.R;

public class MultipleChoiceFragment extends Fragment implements View.OnClickListener {
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private String mAnswer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_multiple_choice, container, false);

        mQuestionView = (TextView) v.findViewById(R.id.question);
        mButtonChoice1 = (Button) v.findViewById(R.id.choice1);
        mButtonChoice2 = (Button) v.findViewById(R.id.choice2);
        mButtonChoice3 = (Button) v.findViewById(R.id.choice3);
        mButtonChoice4 = (Button) v.findViewById(R.id.choice4);

        updateQuestion();

        return v;
    }

    private void updateQuestion() {
//        mQuestionView.setText();
//        mButtonChoice1.setText();
//        mButtonChoice2.setText();
//        mButtonChoice3.setText();
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            Button chosenButton = (Button) v;
            if (chosenButton.getText().toString().equals(mAnswer)) {
                //TODO if correct.
                Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
            }
            else {
                //TODO if wrong.
                Toast.makeText(getActivity(), "Incorrect!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

