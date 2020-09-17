package com.example.asingtesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class historymain extends AppCompatActivity {
    ArrayList<historyDetailsClass> historyArray = new ArrayList<historyDetailsClass>();
    String email, id, companyname;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historymain);
        getData();
        id = email.replace("@", "0");
        id = id.replace(".", "0");
        getSupportActionBar().setTitle("History Details");
        Date date = Calendar.getInstance().getTime();
       // historyDetailsClass userHistory = new historyDetailsClass();
        //userHistory.historyDetails(total, date, companyname);
        //write
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("history Details/" + id);

        //read
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                    historyDetailsClass value = datasnapshot1.getValue(historyDetailsClass.class);
                    historyArray.add(value);

                }
                runRecycle();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    void runRecycle() {

        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.historyRecycler);
        recycleViewClass myAdapter = new recycleViewClass(this, historyArray);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getData() {
        if (getIntent().hasExtra("email")) {
            email = getIntent().getStringExtra("email");
        } else {
            Toast.makeText(historymain.this, "No Data", Toast.LENGTH_SHORT).show();
        }
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
        Intent intent = new Intent(this,customerservices.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }
}