package com.example.asingtesting;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class produciInforecycleview extends RecyclerView.Adapter<produciInforecycleview.MyViewHolder> {

          ArrayList<Product_List_class> pame;
    Context context;

    public produciInforecycleview (Context ct, ArrayList<Product_List_class> pname){
        context = ct;
        this.pame = pname;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.productinfo,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(pame.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return pame.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.product_name);

        }
    }


}
