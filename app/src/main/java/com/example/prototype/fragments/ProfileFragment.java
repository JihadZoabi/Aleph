package com.example.prototype.fragments;

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
import com.example.prototype.StoryActivity;
import com.example.prototype.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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
        xpText.setText("XP: "+ String.valueOf(xp));

        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*mAuth.signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));*/
                Intent i = new Intent(getActivity(), StoryActivity.class);
                ArrayList<String> messages = new ArrayList<String>();
                ArrayList<String> translations = new ArrayList<String>();
                messages.add("Hello, what bus helps me to reach Tel Aviv from Jerusalem");
                messages.add("I think, it's line 480");
                messages.add("What hour would it arrive");
                messages.add("I think at 13:28");
                messages.add("Thank you very much!");
                translations.add("مرحبا، اي باص بوديني عتل ابيب من القدس؟");
                translations.add("بفكر انه باص رقم 480");
                translations.add("اي ساعة بيجي؟");
                translations.add("ممكن 13:28");
                translations.add("شكرا كثير");
                i.putStringArrayListExtra("messages", messages);
                i.putStringArrayListExtra("translations", translations);
                startActivity(i);
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