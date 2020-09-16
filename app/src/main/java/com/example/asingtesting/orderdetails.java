package com.example.asingtesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class orderdetails extends AppCompatActivity {
ArrayList<cartclass> cart =new ArrayList<cartclass>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("history Details");
        DatabaseReference newRef = myRef.push();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                    cartclass value = datasnapshot1.getValue(cartclass.class);
                    cart.add(value);
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
        recyclerView = findViewById(R.id.recyclerView20);
        cartView myAdapter = new cartView( this, cart);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void button(View view)
    {
        Intent intent = new Intent(this,CompanyListMain.class);
        startActivity(intent);

    }
    }

