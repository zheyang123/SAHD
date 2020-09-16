package com.example.asingtesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

public class display_product_list extends AppCompatActivity {

    String company_name = "7-Eleven",email;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef,infoRef;
    ArrayList<Product_List_class> PLArray = new ArrayList<Product_List_class>();
    ArrayList<ProductInfoClass> PIArray = new ArrayList<ProductInfoClass>();
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        try{
            super.onRestoreInstanceState(savedInstanceState);
        }catch (Exception e) {
            System.out.println("Exce");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_product_list);

        getData();
        myRef = database.getReference(company_name) ;
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnap : dataSnapshot.getChildren()) {
                    Product_List_class value = dataSnap.getValue(Product_List_class.class);
                    PLArray.add(value);
                }
                runRecycleView();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        infoRef = database.getReference(company_name+"Info") ;
        infoRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                for (DataSnapshot dataSnap : dataSnapshot.getChildren()) {
                    ProductInfoClass value = dataSnap.getValue(ProductInfoClass.class);
                    PIArray.add(value);
                }
                runRecycleView();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    void runRecycleView() {
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.PL_Recycle);
        Recycleview_class_productlist myAdapter = new Recycleview_class_productlist( this,PLArray,PIArray,company_name,email);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void getData() {
        if (getIntent().hasExtra("companyname")) {
            company_name = getIntent().getStringExtra("companyname");
            email = getIntent().getStringExtra("email");
        }
        else {
            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show();
        }
    }
}