package com.example.prototype;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

public class User implements Serializable {
    private String username;
    private String userID;
    private List<String> progress;
    private int xp;
    private int falafels;
    private String email;
    private String password;

    private DatabaseReference ref;
    private FirebaseAuth auth;
    private Context context;
    private FirebaseDatabase db;

    public<T extends Activity> void logIn(Class<T> activity) {
        Intent i = new Intent(context, activity);
        i.putExtra("user", this);
        context.startActivity(i);
    }

    public List<String> getProgress() { // maybe shouldn't copy
        return new ArrayList<String>(progress);
    }

    private User(String addr, String user, String pass, Context c) {
        email = addr;
        password = pass;
        username = user;
        xp = 0;
        falafels = 0;
        context = c;
        db = FirebaseDatabase.getInstance("https://spokenli-default-rtdb.europe-west1.firebasedatabase.app/");
        progress = null;
        progress = null;
        ref = null /* db.getReference() */;
        auth = null /* FirebaseAuth.getInstance() */;
        userID = null /* auth.getUid() */;
    }

    public void doneLesson(Lesson l) {
        progress.add(l.getName());
        setField("progress", String.join("|", progress));
        xp += l.getXP();
        setField("xp", xp);
    }

    public void getXP(Consumer<Integer> c) {
        getField("xp", c);
    }

    private void basicSetup() {
        auth = FirebaseAuth.getInstance();
        userID = auth.getUid();
        ref = db.getReference("user/" + userID);
    }

    public static User register(Context c, String user, String email,
                                String password, Consumer<Exception> err) {
        User u = new User(email, user, password, c);
        u.auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    u.basicSetup();
                    u.progress = new ArrayList<String>();
                    u.setField("progress", "");
                    u.setField("username", user);
                    u.setField("xp");
                    u.setField("falafels");
                    u.setField("progress", "");
                    u.setField("email", email);
                    Toast.makeText(u.context, "Success!", Toast.LENGTH_SHORT).show();
                    u.context.startActivity(new Intent(u.context, LoginActivity.class));
                }).addOnFailureListener(err::accept);
        return u;
    }

    public static User login(Context c, String email,
                             String password, Consumer<Exception> err) {
        User u = new User(email, "", password, c);
        u.auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    u.<String>getField("progress", p -> {
                        u.progress = new ArrayList<String>(
                                Arrays.asList(p.split("|")));
                    });
                    u.basicSetup();
                    Toast.makeText(c, "Success", Toast.LENGTH_SHORT).show();
                    c.startActivity(new Intent(c, MainActivity.class));
                }).addOnFailureListener(err::accept);
        return u;
    }

    private void setField(String field, Object value) {
        ref.child(field).setValue(value);
    }

    private void setField(String field) {
        setField(field, 0);
    }

    private<T> void getField(String field, Consumer<T> c) {
        ref.child(auth.getUid()).child(field).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                c.accept((T) snapshot.getValue());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    public void changeUsername(String _username){
        username = _username;
        setField("username", username);
    }
}
