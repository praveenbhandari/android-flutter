package com.example.andall;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class fetchData extends AsyncTask<Void, Void, Void> {

String data="",parsed="",p="";


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Log.e("In fetch","started");
            URL url =new URL("https://my-json-server.typicode.com/praveenbhandari/json/data");
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            InputStream is=conn.getInputStream();
            BufferedReader br= new BufferedReader(new InputStreamReader(is));
//String a= br.readLine();

           String line="";
            while(data != null){
                line = br.readLine();
                data =data + line;
                //Log.e("AAAAAAAAAAAAAAAAA",line);
                //

                Log.e("data ",data);
            }
            Log.e("data ",data);
            JSONArray ja=new JSONArray(data);
            for(int i=0;i<ja.length();i++) {
                JSONObject jo = (JSONObject) ja.get(i);
                parsed = "Name = "+jo.get("name")+
                        "Age ="+jo.get("Age")+"Location  =" +jo.get("Location");
                p=p+parsed;
                Log.e("parsed  data",p+"\n");
                conn.disconnect();
            }

          //  conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
       MainActivity.tv.setText(data);
       Log.e("data",p);
       // Log.e("data",data);
    }

}
