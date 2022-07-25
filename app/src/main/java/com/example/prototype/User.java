package com.example.prototype;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;

public class User {
    private String username;
    private String userID;
    private String progress;
    private int xp;
    private int falafels;
    private String email;

    public User(DatabaseReference db, String _username, String _userID, String _email){
        this.username = _username;
        this.userID = _userID;
        this.progress = "";
        this.xp = 0;
        this.falafels = 0;
        this.email = _email;

        db.child(this.userID).child("username").setValue(this.username);
        db.child(this.userID).child("xp").setValue(this.xp);
        db.child(this.userID).child("falafels").setValue(this.falafels);
        db.child(this.userID).child("progress").setValue(this.progress);
        db.child(this.userID).child("email").setValue(this.email);
    }
}
