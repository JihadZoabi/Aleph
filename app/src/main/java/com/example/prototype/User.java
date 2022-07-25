package com.example.prototype;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User{
    private String username;
    private String userID;
    private String progress;
    private int xp;
    private int falafels;
    private String email;
    private String password;

    private DatabaseReference ref;
    private FirebaseAuth auth;
    private Context context;

    public User(String _email, String _password, Context _context){
        this.email = _email;
        this.password = _password;
        this.auth = FirebaseAuth.getInstance();
        this.context = _context;
        this.userID = auth.getUid();
        this.ref = FirebaseDatabase.getInstance("https://spokenli-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("user/");
    }

    //TODO: Register function
    public void registerUser(String _username){
        this.falafels = 0;
        this.xp = 0;
        this.progress = "";
        this.username = _username;

        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                ref.child(FirebaseAuth.getInstance().getUid()).child("username").setValue(username);
                ref.child(FirebaseAuth.getInstance().getUid()).child("xp").setValue(xp);
                ref.child(FirebaseAuth.getInstance().getUid()).child("falafels").setValue(falafels);
                ref.child(FirebaseAuth.getInstance().getUid()).child("progress").setValue(progress);
                ref.child(FirebaseAuth.getInstance().getUid()).child("email").setValue(email);
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        });



    }

    //TODO: Login function
    public void loginUser(){
        this.auth.signInWithEmailAndPassword(this.email, this.password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, MainActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //TODO: change username
    public void changeUsername(String _username){
        this.username = _username;
        ref.child("username").setValue(username);
    }

    //TODO: function increase XP
    public void increaseXP(){}


}
