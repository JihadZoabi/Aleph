package com.example.prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText emailText;
    EditText passwordText;
    Button loginBtn;

    TextView toSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_button);
        toSignup = findViewById(R.id.toSignup);

        toSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });



        mAuth = FirebaseAuth.getInstance();


        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                User user = new User(email, password, LoginActivity.this);
                user.loginUser();
                //loginUser(emailText.getText().toString(), passwordText.getText().toString());
            }
        });

        mAuth = FirebaseAuth.getInstance();
    }

    private void updateUI(FirebaseUser user){
        //TODO: make a useful updateUI function.
    }
}
