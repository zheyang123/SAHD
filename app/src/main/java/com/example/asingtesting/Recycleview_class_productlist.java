package com.example.asingtesting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Recycleview_class_productlist extends RecyclerView.Adapter<Recycleview_class_productlist.MyViewHolder> {
    Context context;
    List<Product_List_class> ProductDetailsList;
    List<ProductInfoClass> ProductInfoList;
   String email;
    String companyname;
    public Recycleview_class_productlist (Context ct, List<Product_List_class> historyDetailsList,List<ProductInfoClass> ProductInfoList,
                                          String companyname,String email){
        this.context = ct;
        this.ProductDetailsList= historyDetailsList;
        this.ProductInfoList=ProductInfoList;
        this.companyname=companyname;
        this.email = email;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_list_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.myText1.setText(ProductDetailsList.get(position).getProductName());
        holder.myText2.setText(String.valueOf(ProductInfoList.get(position).getPrice()));
        Picasso.get().load(ProductDetailsList.get(position).getImage()).into(holder.imageView);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddToCart.class);
               intent.putExtra("productname", ProductDetailsList.get(position).getProductName());
              intent.putExtra("companyname", companyname);
                intent.putExtra("url",ProductDetailsList.get(position).getImage());
                intent.putExtra("email",email);
               // Bundle bun=new Bundle();
               // bun.putDouble("price",ProductInfoList.get(position).getPrice());
               // intent.putExtras(bun);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ProductDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myText1, myText2;
        ImageView imageView;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.PD_Name);
            myText2 = itemView.findViewById(R.id.PD_Price);
            imageView = itemView.findViewById(R.id.PD_Pic);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
