package com.example.asingtesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class historymain extends AppCompatActivity {
    ArrayList<historyDetailsClass> historyArray=new ArrayList<historyDetailsClass>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historymain);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("history Details");
        Date date = Calendar.getInstance().getTime();
        double total = 90.00;
        String companyname = "pet city";
        historyDetailsClass userHistory = new historyDetailsClass();
        userHistory.historyDetails(total,date,companyname);
        DatabaseReference newRef = myRef.push();
        newRef.setValue(userHistory);

        //read
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                    historyDetailsClass value = datasnapshot1.getValue(historyDetailsClass.class);
                    historyArray.add(value);
                    runRecycle();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
    void runRecycle()
    {

        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.historyRecycler);
        recycleViewClass myAdapter = new recycleViewClass( this,historyArray);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}