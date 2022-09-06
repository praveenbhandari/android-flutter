package com.example.country;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.country.db.Country;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CustomList extends BaseAdapter {
    Context context;
ArrayList<Custom_list_data> arrayList;
ArrayList<Country> ccccc;
ImageView flag;
TextView name,capital,region,subregion,language,broders,population,srnu;
//String name,capital,region,subregion,language,broders,population,flag;
    public CustomList(Context context, ArrayList<Custom_list_data> arrayList){
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
//        new downloa().equals("https://restcountries.eu/data/syr.svg");
        flag.setImageResource(R.drawable.wall);
        population.setText(arrayList.get(position).getpopulation());
srnu.setText(arrayList.get(position).getSrnum());


        return convertView;
    }
    public class downloa extends AsyncTask<String,Void, Bitmap> {
        private Bitmap downloadImageBitmap(String sUrl) {
            Log.e("downloadinggg......","............."+sUrl);
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(sUrl).openStream();   // Download Image from URL
                bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
//                saveImage(getApplicationContext(), bitmap, f_name);
                flag.setImageBitmap(bitmap);
                inputStream.close();

            } catch (Exception e) {
                Log.e("DownloadImage", ""+e);
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            return downloadImageBitmap(strings[0]);
        }
        protected void onPostExecute(Bitmap result) {
//            saveImage(getApplicationContext(), result, "my_image.png");
        }

    }

}
