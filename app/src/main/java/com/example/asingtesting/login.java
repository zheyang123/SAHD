package com.example.asingtesting;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity {
    public static final String TAG = "TAG";
    private EditText Password1,Password2,Password3;
    private Button login,logout;
    private Button register;
    private EditText Email1,Email2;
    String userID,custemail;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth = FirebaseAuth.getInstance();
        Password1 = (EditText) findViewById(R.id.editTextTextPassword);
        Password2 = (EditText) findViewById(R.id.editTextTextPassword2);
        Password3 = (EditText) findViewById(R.id.editTextTextPassword3);
        Email1 = (EditText) findViewById(R.id.editTextTextEmailAddress);
        Email2 = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        login = (Button) findViewById(R.id.loginbutton);
        register = (Button) findViewById(R.id.registerbutton);
        //SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        //String checkbox = preferences.getString("stayLogged", "");
        //if (checkbox.equals("false")){
        // Logout
        //FirebaseAuth.getInstance().signOut();
        // Set stay logged checkbox
        //SharedPreferences.Editor editor = preferences.edit();
        //editor.putString("stayLogged","true");
        // editor.apply();
        //}
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email1.getText().toString();
                String pass = Password1.getText().toString();
                if (email.isEmpty()) {
                    Email1.setError("Please enter your email!!!!");
                    Email1.requestFocus();
                } else if (pass.isEmpty()) {
                    Password1.setError("Please enter your password!!!!");
                    Password1.requestFocus();
                } else {

                    mFirebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Intent loginSuccessful = new Intent(MainActivity.this, MainStore.class);//go item page after login
                                //startActivity(loginSuccessful);
                                Toast.makeText(login.this, "Login Successfull!!!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(login.this, "Login Failed!!! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                //progressBar.setVisibility(View.GONE);
                                //bL.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                custemail=Email2.getText().toString();
                String pass1=Password2.getText().toString();
                String pass2=Password3.getText().toString();
                if(custemail.isEmpty())
                {Email2.setError("Please enter your phone number!!!!");
                    Email2.requestFocus();}
                else if(pass1.isEmpty())
                {Password2.setError("Please enter your password!!!!");
                    Password2.requestFocus();}
                else if(pass2.isEmpty())
                {Password3.setError("Please enter your password!!!!");
                    Password3.requestFocus();}
                else if(!pass1.matches("^[a-zA-Z0-9]*$"))
                {Password2.setError("Please try another password!!!!");
                    Password2.requestFocus();}
                else if(pass1.length() < 6)
                {Password2.setError("Your password is too short.Please try again!!!!");
                    Password2.requestFocus();}
                else if(pass1.length() > 14)
                {Password2.setError("Your password is too long.Please try again!!!!");
                    Password2.requestFocus();}
                else if(!pass1.equals(pass2))
                {Password3.setError("Your password is not match.Please try again!!!!");
                    Password3.requestFocus();}
                else
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(custemail,pass1).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(login.this,"SignUp Unsuccessful!!! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                //progressBar.setVisibility(View.GONE);
                                //bCA.setVisibility(View.VISIBLE);
                                //t.setVisibility(View.VISIBLE);
                                //bSI.setVisibility(View.VISIBLE);
                            }else{
                                FirebaseUser fUser = mFirebaseAuth.getCurrentUser();
                                fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(login.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                    }
                                });
                                Toast.makeText(login.this, "User Created.", Toast.LENGTH_SHORT).show();
                                userID = mFirebaseAuth.getCurrentUser().getUid();
                                database = FirebaseDatabase.getInstance();
                                //Write into realtime database
                                myRef = database.getReference("customer").child(userID);

                                myRef.child("userID").setValue(userID);
                                myRef.child("email").setValue(custemail);

                            }
                        }
                    });
                    Toast.makeText(login.this,"Done",Toast.LENGTH_SHORT).show();}
            }
        });


    }
    public void logout(View view){
     FirebaseAuth.getInstance().signOut();
     startActivity(new Intent(getApplicationContext(),login.class));
     finish();
    }

}