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

    String company_name = "watson",wa;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef ;
    ArrayList<Product_List_class> PLArray = new ArrayList<Product_List_class>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_product_list);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                getData();
                wa=company_name;
                myRef = database.getReference(company_name);
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
    }

    void runRecycleView() {
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.PL_Recycle);
        Recycleview_class_productlist myAdapter = new Recycleview_class_productlist( this,PLArray);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void getData() {
        if (getIntent().hasExtra("CompanyRegisterArray")) {
            company_name = getIntent().getStringExtra("CompanyRegisterArray");
        }
        else {
            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show();
        }
    }
}