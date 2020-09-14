package com.example.asingtesting;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

public class CompanyListRecycleView extends RecyclerView.Adapter<CompanyListRecycleView.MyViewHolder>{
    Context context;
        List<companyRegisterClass> CompanyRegisterArray;
        ArrayList <Bitmap> bitmaps;
        public CompanyListRecycleView (Context ct, List<companyRegisterClass> CompanyArray,ArrayList<Bitmap> bitmaps){
            this.context = ct;
            this.CompanyRegisterArray = CompanyArray;
            this.bitmaps = bitmaps;
        }

        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater =LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.company_list_rv,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.myText1.setText(CompanyRegisterArray.get(position).getCompany_name());
            holder.myText2.setText(CompanyRegisterArray.get(position).getCompany_address());
            holder.myImg.setImageBitmap(bitmaps.get(position));
        }

        @Override
        public int getItemCount() {
            return CompanyRegisterArray.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView myText1, myText2;
            ImageView myImg;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                myText1 = itemView.findViewById(R.id.companyNameLabel);
                myText2 = itemView.findViewById(R.id.companyAddressLabel);
                myImg = itemView.findViewById(R.id.companyimage);
            }
        }
    }