package com.example.asingtesting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Company_Main_Page extends AppCompatActivity {
    String companyname = "SAD";
    ArrayList<Bitmap> ImgArray = new ArrayList<Bitmap>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("company Details");
    ArrayList<companyRegisterClass> CompanyRegisterArray = new ArrayList<companyRegisterClass>();
    FirebaseStorage ComImg = FirebaseStorage.getInstance();
    StorageReference ImgRef = ComImg.getReference().child("Company List").child(companyname);
    RecyclerView recyclerView;
    CompanyListRecycleView myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_company__main__page);
        super.onCreate(savedInstanceState);
        EditText editText = findViewById(R.id.search_Bar);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                filter(editable.toString());
            }
        });
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datasnapshot1 : dataSnapshot.getChildren()) {
                    companyRegisterClass value = datasnapshot1.getValue(companyRegisterClass.class);
                    CompanyRegisterArray.add(value);
                }
                for (int i = 0;i<CompanyRegisterArray.size();i++)
                {
                    StorageReference ImgRef = ComImg.getReference().child("Company List").child(CompanyRegisterArray.get(i).getCompany_name());
                    ImgRef.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            ImgArray.add(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle failed download
                        }
                    });
                }
                CompanyrecyclerView();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

    }
    private void filter(String t)
    {
        ArrayList<companyRegisterClass> filterList = new ArrayList<>();
        for (companyRegisterClass item:CompanyRegisterArray)
            if (item.getCompany_name().toLowerCase().contains(t.toLowerCase()))
            {
                filterList.add(item);
            }
        myAdapter.filterList(filterList);
    }
    void CompanyrecyclerView() {
    recyclerView = findViewById(R.id.CompanyrecyclerView);
    myAdapter = new CompanyListRecycleView(this, CompanyRegisterArray,ImgArray);
    recyclerView.setAdapter(myAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
}
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
    public void orderhistory(View view)
    {
        Intent intent = new Intent(this,historymain.class);
        startActivity(intent);
    }
    public void registerbusiness(View view)
    {
        Intent intent = new Intent(this,CompanyListMain.class);
        startActivity(intent);
    }
    
}