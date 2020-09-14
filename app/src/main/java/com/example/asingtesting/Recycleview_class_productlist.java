package com.example.asingtesting;

import android.content.Context;
import android.content.Intent;
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
    public Recycleview_class_productlist (Context ct, List<Product_List_class> historyDetailsList){
        this.context = ct;
        this.ProductDetailsList= historyDetailsList;
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
        holder.myText2.setText(String.valueOf(ProductDetailsList.get(position).getPrice()));
        Picasso.get().load(ProductDetailsList.get(position).getImage()).into(holder.imageView);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, product_mini_layout.class);
                intent.putExtra("suibian", ProductDetailsList.get(position).getProductName());
                intent.putExtra("suibian2", (String.valueOf(ProductDetailsList.get(position).getPrice())));
                intent.putExtra("url",ProductDetailsList.get(position).getImage());
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
