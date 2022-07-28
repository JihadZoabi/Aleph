package com.example.prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailText;
    private EditText passwordText;
    private Button loginBtn;
    private TextView toSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_button);
        toSignup = findViewById(R.id.toSignup);
        toSignup.setOnClickListener(view ->
                startActivity(new Intent(LoginActivity.this, SignupActivity.class)));
        loginBtn.setOnClickListener(view -> {
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();
            User user = User.login(this, email, password,
                    e -> Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show());
            user.logIn(MainActivity.class);
        });
    }
}
