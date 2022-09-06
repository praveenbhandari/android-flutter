package com.example.country;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.country.db.Country;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class connectivity extends BaseAdapter {
    Context context;
    List<Country> arrayList;
//    ArrayList<Country> ccccc;
    ImageView flag;
    TextView name,capital,region,subregion,language,broders,population,srnu;
    //String name,capital,region,subregion,language,broders,population,flag;
    public connectivity(Context context, List<Country> arrayList){
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
        convertView = LayoutInflater.from(context).inflate(R.layout.list_itemm,parent,false);
        name=convertView.findViewById(R.id.c_name);
        capital=convertView.findViewById(R.id.c_capit);
        region=convertView.findViewById(R.id.region);
        subregion=convertView.findViewById(R.id.subregion);
        broders=convertView.findViewById(R.id.border);
        population=convertView.findViewById(R.id.population);
        flag=convertView.findViewById(R.id.flag);
        language=convertView.findViewById(R.id.language);
        srnu=convertView.findViewById(R.id.sr_no_1);
//downloadImage(arrayList.get(position).getflag());
        name.setText(arrayList.get(position).getName());
        capital.setText(arrayList.get(position).getcapti());
        region.setText(arrayList.get(position).getregion());
        subregion.setText(arrayList.get(position).getsubregion());
        language.setText(arrayList.get(position).getLanguages());
        broders.setText(arrayList.get(position).getborders());
//        flag.back(arrayList.get(position).getflag());
        flag.setImageResource(R.drawable.wall);
        population.setText(arrayList.get(position).getpopulation());
        srnu.setText(arrayList.get(position).getSrnum());


        return convertView;
    }


}
