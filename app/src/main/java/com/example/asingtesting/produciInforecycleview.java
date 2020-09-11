package com.example.asingtesting;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class produciInforecycleview extends RecyclerView.Adapter<produciInforecycleview.MyViewHolder> {

        String data1[];
        Context context;

        public produciInforecycleview (Context ct, String s1[]){
            context = ct;
            data1 = s1;
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
            holder.myText1.setText(data1[position]);

        }

        @Override
        public int getItemCount() {
            return data1.length;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView myText1;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                myText1 = itemView.findViewById(R.id.product_name);



            }
        }
}
