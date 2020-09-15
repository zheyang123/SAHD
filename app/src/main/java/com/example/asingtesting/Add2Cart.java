package com.example.asingtesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Add2Cart extends AppCompatActivity {
    String companyName = "Watson";
    String product_name = "XueHuaPiaoPiao";
    String p;
    ArrayList<ProductInfoClass> arraylist = new ArrayList<ProductInfoClass>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2_cart);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(companyName + "Info");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot){

                for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                    ProductInfoClass value = datasnapshot1.getValue(ProductInfoClass.class);
                    arraylist.add(value);
                }
                for(int i = 0; i < arraylist.size(); i++) {
                    p = arraylist.get(i).getProductName();
                    if(product_name.equals(p)) {
                        TextView desc = findViewById(R.id.product_desc);
                        TextView productName = findViewById(R.id.product_name);
                        desc.setText(arraylist.get(i).getDescription());
                        productName.setText(arraylist.get(i).getProductName());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }

}