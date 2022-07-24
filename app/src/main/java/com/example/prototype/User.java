package com.example.prototype;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;

public class User {
    private String username;
    private String userID;
    private String progress;
    private int xp;
    private int falafels;

    public User(DatabaseReference db, String _username, String _userID){
        this.username = _username;
        this.userID = _userID;
        this.progress = "";
        this.xp = 0;
        this.falafels = 0;

        db.child(userID).child("username").setValue(this.username);
        Log.d("username", username);
    }
}
