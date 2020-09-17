package com.example.asingtesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class orderdetails extends AppCompatActivity {
ArrayList<cartclass> cart =new ArrayList<cartclass>();
String email,id,companyName;
    cartView myAdapter;
    double total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        getData();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        id =email.replace("@","0");
        id =id.replace(".","0");
        DatabaseReference myRef = database.getReference(companyName+id+"cart");
        DatabaseReference newRef = myRef.push();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                    cartclass value = datasnapshot1.getValue(cartclass.class);
                    cart.add(value);

                }
                runRecycle();
                total = myAdapter.gettotal();
                TextView textView=findViewById(R.id.totalprice);
                textView.setText(String.valueOf(total));
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
        myAdapter = new cartView( this, cart,companyName,email);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void button(View view)
    {
        Date date = Calendar.getInstance().getTime();


        historyDetailsClass userHistory = new historyDetailsClass();
        userHistory.historyDetails(total,date,companyName);
        //write
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("history Details/"+id);
        DatabaseReference newRef = myRef.push();
        newRef.setValue(userHistory);
        DatabaseReference inRef = database.getReference(companyName+id+"cart");
        inRef.setValue("blank");
        Toast.makeText(orderdetails.this,"Order Successful!!",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,Company_Main_Page.class);
        intent.putExtra("email",email);
        finish();
        startActivity(intent);
    }
    private void getData() {
        if (getIntent().hasExtra("companyname")) {
            companyName = getIntent().getStringExtra("companyname");
            email = getIntent().getStringExtra("email");
        } else {
            Toast.makeText(orderdetails.this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    }

