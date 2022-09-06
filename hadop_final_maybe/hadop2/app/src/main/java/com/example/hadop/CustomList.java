package com.example.hadop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomList extends BaseAdapter {
    Context context;
ArrayList<Custom_list_data> arrayList;
TextView name,date,sympt,srno;
    public CustomList(Context context,ArrayList<Custom_list_data> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_listttt_1,parent,false);
        name=convertView.findViewById(R.id.patient_name_1);
        date=convertView.findViewById(R.id.date_1);
        sympt=convertView.findViewById(R.id.patient_symptoms_1);
        srno=convertView.findViewById(R.id.sr_no_1);
        srno.setText(arrayList.get(position).getSrnum());
        name.setText(arrayList.get(position).getName());
        date.setText(arrayList.get(position).getDate());
        sympt.setText(arrayList.get(position).getSymptoms());


        return convertView;
    }
}
