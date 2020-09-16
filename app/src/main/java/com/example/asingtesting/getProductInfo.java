package com.example.asingtesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class getProductInfo extends AppCompatActivity {
    ArrayList<ProductInfoClass> arraylist = new ArrayList<ProductInfoClass>();
    ArrayList<Product_List_class> proname = new ArrayList<Product_List_class>();
    RecyclerView recyclerView;
    String companyName="watson";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_product_info);
        Bundle bundle = getIntent().getExtras();
       companyName = bundle.getString("companyname");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(companyName);
        DatabaseReference newRef = myRef.push();

        //read
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                    Product_List_class value = datasnapshot1.getValue(Product_List_class.class);
                    proname.add(value);
                     runRecycle();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }

    public void btFunc(View view) {
        for (int i = 0; i < proname.size(); i++) {
            View v = recyclerView.getChildAt(i);
            EditText nameEditText = (EditText) v.findViewById(R.id.product_desc);
            EditText alternativeText = v.findViewById(R.id.priceText);
            TextView product_name = v.findViewById(R.id.product_name);
            String productname = product_name.getText().toString();
            String desc = nameEditText.getText().toString();
            String priceString = alternativeText.getText().toString();
            double value = Double.parseDouble(priceString);
            ProductInfoClass info = new ProductInfoClass();
            info.setInfo(productname, desc, value);
            arraylist.add(info);
            Toast.makeText(getProductInfo.this, "register suscessful!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,Company_Main_Page.class);
            startActivity(intent);
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(companyName + "Info");
        for(int i = 0; i < arraylist.size(); i++){
            DatabaseReference newRef = myRef.push();
            newRef.setValue(arraylist.get(i));
        }

    }
    void runRecycle()
    {
        recyclerView = findViewById(R.id.recycle);
        produciInforecycleview rv = new produciInforecycleview(this,proname);
        recyclerView.setAdapter(rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

