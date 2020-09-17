package com.example.asingtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class customerservices extends AppCompatActivity {
String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerservices);
        getSupportActionBar().setTitle("Customer Service");
        email = getIntent().getStringExtra("email");
    }

    public void orderhistory(View view)
    {
        Intent intent = new Intent(this,historymain.class);
        intent.putExtra("email",email);

        startActivity(intent);
    }
    public void registerbusiness(View view)
    {
        Intent intent = new Intent(this,CompanyListMain.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }
    public void home(View view)
    {
        Intent intent = new Intent(this,Company_Main_Page.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }
    public void aboutus(View view)
    {
        Intent intent = new Intent(this,aboutus.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }
    public void customerservices(View view)
    {
        Intent intent = new Intent(this,customerservices.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }
    public void myprofile(View view)
    {
        Intent intent = new Intent(this,profile.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }
}