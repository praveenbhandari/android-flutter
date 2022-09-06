package com.example.javaproj;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {

    private ArrayList<dataa> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bname;
//        TextView desti;
        TextView fare;
        TextView time;
        TextView from;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.card_view);
            this.bname = (TextView) itemView.findViewById(R.id.bname);
//            this.desti = (TextView) itemView.findViewById(R.id.bdesc);
            this.fare = (TextView) itemView.findViewById(R.id.fare);
            this.time = (TextView) itemView.findViewById(R.id.time);
            this.from = (TextView) itemView.findViewById(R.id.bfrom);
        }
    }

    public adapter(ArrayList<dataa> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        view.setOnClickListener(main.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView busname = holder.bname;
//        TextView destination = holder.desti;
        TextView fare1 = holder.fare;
        TextView time1 = holder.time;
        TextView from1=holder.from;

        busname.setText(dataSet.get(listPosition).getName());
//        destination.setText(dataSet.get(listPosition).getDestination());
        fare1.setText(dataSet.get(listPosition).getfare());
        time1.setText(dataSet.get(listPosition).gettime());
        from1.setText(dataSet.get(listPosition).getfrom()+ "->"+dataSet.get(listPosition).getDestination());

        if(dataSet.get(listPosition).getColoe().equals("red")){
//            holder.cardView.setBackgroundColor(Color.RED);

            holder.cardView.setCardBackgroundColor(Color.rgb(210,50,50));
//            holder.cardView.setEnabled(false);
//            notifyDataSetChanged();
//            holder
        }


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}