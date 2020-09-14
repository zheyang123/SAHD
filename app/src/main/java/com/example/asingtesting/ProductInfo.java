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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class ProductInfo extends AppCompatActivity {
    String s1[] = {"lolololololololololol", "XueHuaPiaoPiao", "Neko-Charm"};
    ArrayList<ProductInfoClass> arraylist = new ArrayList<ProductInfoClass>();
    RecyclerView recyclerView;
    int stringLength = s1.length;
    String companyName = "Watson";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        recyclerView = findViewById(R.id.urid);
        produciInforecycleview rv = new produciInforecycleview(this, s1);
        recyclerView.setAdapter(rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

}
