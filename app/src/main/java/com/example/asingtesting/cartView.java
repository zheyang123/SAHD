package com.example.asingtesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class cartView extends RecyclerView.Adapter<cartView.MyViewHolder> {
    Context context;
   ArrayList<cartclass> cart;

    public cartView (Context ct, ArrayList<cartclass> cart){
        this.context = ct;
       this.cart=cart;
    }

public double gettotal()
{
    double total =0;
    for(int i =0;i<cart.size();i++)
    {
        total+=cart.get(i).getprice();
    }
    return total;
}
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cartdesign,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //String price =new double(historyDetailsList.get(position).getTotalprice.toString);
        holder.myText1.setText(String.valueOf(cart.get(position).getprice()));
        holder.myText2.setText(cart.get(position).getProductname().toString());


    }



    @Override
    public int getItemCount() {
        return cart.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.price);
            myText2 = itemView.findViewById(R.id.name);



        }
    }
}
