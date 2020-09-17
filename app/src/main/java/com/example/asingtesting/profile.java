package com.example.asingtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
String email,id;
    TextView Uemail,phone,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("My Profile");
        email = getIntent().getStringExtra("email");
        id = email.replace("@", "0");
        id = id.replace(".", "0");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("profile/" + id);

      Uemail=findViewById(R.id.email);
      phone=findViewById(R.id.phoneNumber);
      name =findViewById(R.id.username);
      id = email.replace("@", "0");
      id = id.replace(".", "0");
        // historyDetailsClass userHistory = new historyDetailsClass();
        //userHistory.historyDetails(total, date, companyname);
        //write

        //read
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                profileClass value = dataSnapshot.getValue(profileClass.class);
                    Uemail.setText(email);
                    phone.setText(value.getPhonenumber());
                    name.setText(value.getUserName());

            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }



    public void edit(View view)
    {
        Intent intent = new Intent(this,edidtprofile.class);
        intent.putExtra("email",email);
        startActivity(intent);
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