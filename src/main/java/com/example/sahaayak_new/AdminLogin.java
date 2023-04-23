package com.example.sahaayak_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void goBack(View view) {
        Intent i = new Intent(AdminLogin.this, MainActivity.class);
        startActivity(i);
    }

    public void login(View view) {
        Intent i = new Intent(AdminLogin.this, AdminDashboard.class);
        startActivity(i);
    }
}