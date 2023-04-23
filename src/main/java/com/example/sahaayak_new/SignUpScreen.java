package com.example.sahaayak_new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpScreen extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
//    public boolean validateEmail(){
//        EditText em = findViewById(R.id.emailInput);
//        String email = em.getText().toString();
//        if(email.isEmpty()){
//            em.setError("E-mail field cannot be empty");
//            return false;
//        } else {
//            em.setError(null);
//            return true;
//        }
//    }
//
//    public boolean validatePassword(){
//        EditText pass = findViewById(R.id.passInput);
//        String password = pass.getText().toString();
//        if(password.isEmpty()){
//            pass.setError("Password field cannot be empty");
//            return false;
//        } else if(password.length()<8){
//            pass.setError("Password length cannot be less than 8 characters.");
//            return false;
//        }else {
//            pass.setError(null);
//            return true;
//        }
//    }
//
//    public boolean validateFirstName(){
//        EditText f = findViewById(R.id.fNameInput);
//        String email = f.getText().toString();
//        if(email.isEmpty()){
//            f.setError("First Name field cannot be empty");
//            return false;
//        } else {
//            f.setError(null);
//            return true;
//        }
//    }

    public void insertUser(View view) {

        EditText first = findViewById(R.id.fNameInput);
        EditText last = findViewById(R.id.lNameInput);
        EditText em = findViewById(R.id.emailInput);
        EditText pass = findViewById(R.id.passInput);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        String firstName = first.getText().toString().trim();
        String lastName = last.getText().toString().trim();
        String email = em.getText().toString().trim();
        String password = pass.getText().toString().trim();

        HelperClass hc = new HelperClass();
        hc.setFirstName(firstName);
        hc.setLastName(lastName);
        hc.setEmail(email);
        hc.setPassword(password);

        DatabaseReference newref = reference.child("users").push();
        newref.setValue(hc);
        Toast.makeText(SignUpScreen.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(SignUpScreen.this, UserDashboard.class);
        i.putExtra("email", email);
        startActivity(i);


//        SQLiteDatabase db = openOrCreateDatabase("database", MODE_PRIVATE, null);
//        // might need to add a key attribute in table
//        db.execSQL("create table if not exists users(fname varchar(15), lname varchar(15), email varchar(15), password varchar(10));");
//
//        if(email.getText().toString().equals(" ") || password.getText().toString().equals(" ") || first.getText().toString().equals(" ") || last.getText().toString().equals(" ")){
//            AlertDialog.Builder ADBuilder = new AlertDialog.Builder(this);
//            ADBuilder.setTitle("Fields empty.").setMessage("One or more required fields are empty.").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int i) {
//                    dialog.cancel();
//                }
//            });
//
//            AlertDialog alert = ADBuilder.create();
//            alert.show();
//        }
//
//        else if(email.getText()!=null) {
//            Cursor results = db.rawQuery("select email from users;", null);
//            results.moveToFirst();
//            for(int i=1;i<=results.getCount();i++){
//                if(email.getText().toString().equals(results.getString(3))){
//                    AlertDialog.Builder ADBuilder = new AlertDialog.Builder(this);
//                    ADBuilder.setTitle("Email already registered");
//                    ADBuilder.setMessage("Please use a new email to register.").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int i) {
//                            dialog.cancel();
//                        }
//                    });
//
//                    AlertDialog alert = ADBuilder.create();
//                    alert.show();
//
//                }
//            }
//        }
//
//        else if(password.getText().toString().length()<8){
//            AlertDialog.Builder ADBuilder = new AlertDialog.Builder(this);
//            ADBuilder.setTitle("Invalid Password");
//            ADBuilder.setMessage("Password must be at least 8 characters long.").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int i) {
//                    dialog.cancel();
//                }
//            });
//
//            AlertDialog alert = ADBuilder.create();
//            alert.show();
//        }
//
//        else {
//            String command = "insert into users values ('"+first.getText().toString()+"', '"+last.getText().toString()+"', '"+email.getText().toString()+"', '"+password.getText().toString()+"');";
//            db.execSQL(command);
//            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(SignUpScreen.this, UserDashboard.class);
//            i.putExtra("firstName", first.getText().toString());
//            i.putExtra("lastName", last.getText().toString());
//            db.close();
//            startActivity(i);
//        }
    }

    public void goBack(View view) {
        Intent i = new Intent(SignUpScreen.this, MainActivity.class);
        startActivity(i);
    }
}