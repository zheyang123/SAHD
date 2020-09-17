package com.example.asingtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class edidtprofile extends AppCompatActivity {
String phoneNum,name,id,email;
    EditText phoneNumber;
    EditText userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edidtprofile);
        email = getIntent().getStringExtra("email");
         phoneNumber = findViewById(R.id.phoneNum);
         userName = findViewById(R.id.nameedidt);


    }
    public void edit(View view)
    {
        phoneNum = phoneNumber.getText().toString();
        name =userName.getText().toString();
        id = email.replace("@", "0");
        id = id.replace(".", "0");
        profileClass profile = new profileClass();
        profile.setprofile(name, phoneNum);
        //write
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("profile/" + id);
        myRef.setValue(profile);

        Toast.makeText(edidtprofile.this, "edit Successful!!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, profile.class);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }
}