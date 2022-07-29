package com.example.prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    private EditText emailText;
    private EditText usernameText;
    private EditText passwordText;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        emailText = findViewById(R.id.email);
        usernameText = findViewById(R.id.username);
        passwordText = findViewById(R.id.password);
        signupBtn = findViewById(R.id.signup_button);
        signupBtn.setOnClickListener(view -> {
            String email = emailText.getText().toString();
            String username = usernameText.getText().toString();
            String password = passwordText.getText().toString();
            if (email.length() >= 1 && password.length() >= 1) {
                User.register(this, username, email, password,
                        e -> Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show());
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
            Toast.makeText(this, "Please enter required fields.", Toast.LENGTH_SHORT).show();
        });
    }
}