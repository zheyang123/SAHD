package com.example.asingtesting;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.common.primitives.Bytes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CompanyListRecycleView extends RecyclerView.Adapter<CompanyListRecycleView.MyViewHolder>{
    Context context;
        List<companyRegisterClass> CompanyRegisterArray;
        ArrayList <Bitmap> bitmaps;
        String email;
        public CompanyListRecycleView (Context ct, List<companyRegisterClass> CompanyArray,String email){
            this.context = ct;
            this.CompanyRegisterArray = CompanyArray;
            this.bitmaps = bitmaps;
            this.email=email;
        }

        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater =LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.company_list_rv,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
            holder.myText1.setText(CompanyRegisterArray.get(position).getCompany_name());
            holder.myText2.setText(CompanyRegisterArray.get(position).getCompany_address());
            Picasso.get().load(CompanyRegisterArray.get(position).geturl()).into(holder.myImg);
            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,display_product_list.class);
                    intent.putExtra("companyname",CompanyRegisterArray.get(position).getCompany_name());
                    intent.putExtra("email",email);
                    //intent.putExtra("MyImg",CompanyRegisterArray.get(position).geturl());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return CompanyRegisterArray.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView myText1, myText2;
            ImageView myImg;
            ConstraintLayout mainLayout;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                myText1 = itemView.findViewById(R.id.companyNameLabel);
                myText2 = itemView.findViewById(R.id.companyAddressLabel);
                myImg = itemView.findViewById(R.id.companyimage);
                mainLayout = itemView.findViewById(R.id.mainLayout);
            }
        }
        public void filterList(ArrayList<companyRegisterClass> filterList)
        {
            CompanyRegisterArray = filterList;
            notifyDataSetChanged();
        }
    }