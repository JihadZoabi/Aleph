package com.example.prototype.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.prototype.LoginActivity;
import com.example.prototype.R;
import com.example.prototype.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ProfileFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Button signoutBtn;
    FirebaseAuth mAuth;
    TextView userText;
    DatabaseReference ref;
    TextView greetingText;
    TextView xpText;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        signoutBtn = v.findViewById(R.id.signout_button);
        greetingText = v.findViewById(R.id.greeting);

        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance("https://spokenli-default-rtdb.europe-west1.firebasedatabase.app/").getReference("user/");
        xpText = v.findViewById(R.id.xp_text);

        int xp = User.getXP(mAuth.getUid());
        Log.d("xp", String.valueOf(xp));
        xpText.setText("XP: "+ xp);

        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        ref.getRef()
                .child(Objects.requireNonNull(mAuth.getUid()))
                .child("username")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String value = snapshot.getValue(String.class);
                        greetingText.setText(value);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });

        return v;
    }
}