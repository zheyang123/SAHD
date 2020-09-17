package com.example.asingtesting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class cartView extends RecyclerView.Adapter<cartView.MyViewHolder> {
    Context context;
   ArrayList<cartclass> cart;
   String companyname,email;

    public cartView (Context ct, ArrayList<cartclass> cart,String companyname,String email){
        this.context = ct;
       this.cart=cart;
       this.companyname=companyname;
       this.email=email;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        //String price =new double(historyDetailsList.get(position).getTotalprice.toString);
        holder.myText1.setText(String.valueOf(cart.get(position).getprice()));
        holder.myText2.setText(cart.get(position).getProductname().toString());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.remove(position);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String id =email.replace("@","0");
                id =id.replace(".","0");

                DatabaseReference myRef = database.getReference(companyname+id+"cart");
                myRef.setValue("blank");

                for (int i=0;i<cart.size();i++)
                {
                    DatabaseReference newRef = myRef.push();
                    newRef.setValue(cart.get(i));
                }




                Intent intent = new Intent(context, orderdetails.class);
                intent.putExtra("companyname", companyname);
                intent.putExtra("email",email);
                Bundle bun=new Bundle();
                intent.putExtras(bun);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }



    @Override
    public int getItemCount() {
        return cart.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3;
       Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.price);
            myText2 = itemView.findViewById(R.id.name);
button = itemView.findViewById(R.id.remove);



        }
    }
}
