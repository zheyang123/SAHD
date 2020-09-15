package com.example.asingtesting;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class product_mini_layout extends AppCompatActivity {
    ImageView mainImgV;
    TextView mainTxtV;
    TextView mainTxtV2;
    String price;
    String url;
    String ProductDetailsList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_layout2);

        mainTxtV = findViewById(R.id.layout2_name);
        mainTxtV2 = findViewById(R.id.layout2_price);
        mainImgV = findViewById(R.id.layout2_img);
        getData();
        setData();
    }

        private void getData() {
            if (getIntent().hasExtra("suibian") && getIntent().hasExtra("suibian2") && getIntent().hasExtra("url")) {
                ProductDetailsList = getIntent().getStringExtra("suibian");
                price = getIntent().getStringExtra("suibian2");
                url = getIntent().getStringExtra("url");
            }
        else {
            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        mainTxtV.setText(ProductDetailsList);
        mainTxtV2.setText(price);
        Picasso.get().load(url).into(mainImgV);
    }
}
