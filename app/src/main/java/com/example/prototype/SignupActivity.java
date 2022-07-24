package com.example.prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    EditText emailText;
    EditText passwordText;
    Button signupBtn;

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        signupBtn = findViewById(R.id.signup_button);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("/user");



        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                if(!(email.length() < 1 || password.length() < 1)){
                    registerUser(email, password);
                }
            }
        });
    }

    private void registerUser(String _email, String _password){
        mAuth.createUserWithEmailAndPassword(_email, _password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(SignupActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                User user = new User(ref, "eyal", mAuth.getUid());

                ref.child("example-user").child("xp").setValue(5000);

                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }
}