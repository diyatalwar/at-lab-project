package com.example.sahaayak_new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        // Not calling **super**, disables back button in current screen.
    }

    public boolean validateEmail(){
        EditText em = findViewById(R.id.emailInput);
        String email = em.getText().toString();
        if(email.isEmpty()){
            em.setError("E-mail field cannot be empty");
            return false;
        } else {
            em.setError(null);
            return true;
        }
    }

    public boolean validatePassword(){
        EditText pass = findViewById(R.id.passInput);
        String password = pass.getText().toString();
        if(password.isEmpty()){
            pass.setError("Password field cannot be empty");
            return false;
        } else if(password.length()<8){
            pass.setError("Password length cannot be less than 8 characters.");
            return false;
        }else {
            pass.setError(null);
            return true;
        }
    }

    public void checkUser(){
        EditText em = findViewById(R.id.emailInput);
        EditText pass = findViewById(R.id.passInput);

        String email = em.getText().toString().trim();
        String password = pass.getText().toString().trim();

        reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("email").equalTo(email);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    em.setError(null);
                    String passwordFromDB = snapshot.child(email).child("password").getValue(String.class);

                    if(passwordFromDB.equals(password)){
                        em.setError(null);
                        Intent i = new Intent(MainActivity.this, UserDashboard.class);
                        i.putExtra("email", email);
                        startActivity(i);
                    } else {
                        em.setError("Invalid Credentials");
                        pass.requestFocus();
                    }
                } else {
                    em.setError("User does not exist. Please sign up first");
                    em.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void login(View view) {
        if(!validateEmail() | !validatePassword()){

        } else {
            checkUser();
        }
    }

    public void signUp(View view) {
        Intent i = new Intent(MainActivity.this, SignUpScreen.class);
        startActivity(i);
    }

    public void adminLogin(View view) {
        Intent i = new Intent(MainActivity.this, AdminLogin.class);
        startActivity(i);
    }

}