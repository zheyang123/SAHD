package com.example.asingtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AddToCart extends AppCompatActivity {
    String companyName = "";
    String url;
    String product_name = "";
    String p,email;
    double price;
    cartclass adding=new cartclass();
    int num=0;
    ArrayList<ProductInfoClass> arraylist = new ArrayList<ProductInfoClass>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        getData();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(companyName + "Info");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            //product list recycle,displayproductlist

            @Override
            public void onDataChange (DataSnapshot dataSnapshot){

                for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                    ProductInfoClass value = datasnapshot1.getValue(ProductInfoClass.class);
                    arraylist.add(value);
                }
                for(int i = 0; i < arraylist.size(); i++) {
                    p = arraylist.get(i).getProductName();
                    if(product_name.equals(p)) {
                        TextView desc = findViewById(R.id.dectext);
                        TextView productName = findViewById(R.id.pdnametext);
                        desc.setText(arraylist.get(i).getDescription());
                        productName.setText(arraylist.get(i).getProductName());
                        ImageView imageview = findViewById(R.id.pdimage);
                        Picasso.get().load(url).into(imageview);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
    private void getData() {
        if (getIntent().hasExtra("companyname")) {
            companyName = getIntent().getStringExtra("companyname");
            email = getIntent().getStringExtra("email");
            product_name = getIntent().getStringExtra("productname");
            url = getIntent().getStringExtra("url");
            price = getIntent().getExtras().getDouble("price");
        } else {
            Toast.makeText(AddToCart.this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }


    ArrayList<cartclass> cart = new ArrayList<cartclass>();
    public void attocart(View view)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String id =email.replace("@","0");
         id =id.replace(".","0");
        DatabaseReference myRef = database.getReference(companyName+id+"cart");

        adding.setcart(product_name,price,companyName);
            DatabaseReference newRef = myRef.push();
            newRef.setValue(adding);

        Intent intent = new Intent(this,display_product_list.class);
        intent.putExtra("email",email);
        intent.putExtra("companyname",companyName);
        startActivity(intent);
    }

}