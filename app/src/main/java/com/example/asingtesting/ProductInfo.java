package com.example.asingtesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class ProductInfo extends AppCompatActivity {
     //ArrayList<> = new ArrayList<Question>();
   // questions = (ArrayList<Question>)getIntent().getSerializableExtra("QuestionListExtra");
    String s1[];
    ArrayList<ProductInfoClass> arraylist = new ArrayList<ProductInfoClass>();
    RecyclerView recyclerView;
    int stringLength = s1.length;
    String companyName = "Watson";
    ArrayList<Product_List_class> proname = new ArrayList<Product_List_class>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        setContentView(R.layout.activity_product_list_main);
        Bundle bundle = getIntent().getExtras();
        companyName = bundle.getString("companyname");
        Toast.makeText(ProductInfo.this, companyName, Toast.LENGTH_LONG).show();
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
                  //  runRecycle();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }

    public void doneBTFunc(View view) {
        for (int i = 0; i < s1.length; i++) {
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
        recyclerView = findViewById(R.id.urid);
        produciInforecycleview rv = new produciInforecycleview(this,proname);
        recyclerView.setAdapter(rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
