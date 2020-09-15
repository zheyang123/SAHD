package com.example.asingtesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ImageView mainImageView;
    TextView CompanyName;
    String CompanyRegisterArray;
    String url;
    //int MyImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //mainImageView = findViewById(R.id.mainImageView);
        CompanyName = findViewById(R.id.CompanyName);
        mainImageView = findViewById(R.id.mainImageView);

        getData();
        setData();
    }
    private void getData(){
        if (getIntent().hasExtra("CompanyRegisterArray")&&getIntent().hasExtra("url")){

            CompanyRegisterArray = getIntent().getStringExtra("CompanyRegisterArray");
            url = getIntent().getStringExtra("url");
            //MyImg = getIntent().getIntExtra("MyImg",1);

        }else {
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }
    private void setData(){
        CompanyName.setText(CompanyRegisterArray);
        Picasso.get().load(url).into(mainImageView);
        //mainImageView.setImageResource(MyImg);
    }
}