package com.example.asingtesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class recycleViewClass extends RecyclerView.Adapter<recycleViewClass.MyViewHolder> {
    Context context;
    String date[];
    List<historyDetailsClass> historyDetailsList;
    public recycleViewClass (Context ct, List<historyDetailsClass> historyDetailsList){
        this.context = ct;
        this.historyDetailsList= historyDetailsList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //String price =new double(historyDetailsList.get(position).getTotalprice.toString);
        holder.myText1.setText(historyDetailsList.get(position).getCompanyName());
        holder.myText2.setText(historyDetailsList.get(position).getOrderDate().toString());
        holder.myText3.setText(String.valueOf( historyDetailsList.get(position).getTotalprice()).replace("GMT+00:00","/"));

    }



    @Override
    public int getItemCount() {
        return historyDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2, myText3;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.historycomName);
            myText2 = itemView.findViewById(R.id.orderDate);
            myText3 = itemView.findViewById(R.id.price);
            // myImage = itemView.findViewById(R.id.imageView);


        }
    }
}
