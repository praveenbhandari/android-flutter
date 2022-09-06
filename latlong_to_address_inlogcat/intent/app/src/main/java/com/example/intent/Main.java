package com.example.intent;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class Main extends AppCompatActivity {
   String result;
    WebView tv;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button setTime = findViewById(R.id.setTime);
        tv = findViewById(R.id.data);
         t = findViewById(R.id.textView);

readWebpage();
        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
        try {
            List<Address> addresses = geocoder.getFromLocation(19.074329, 72.843590, 1);

            if (addresses.size() > 0) {
                Address fetchedAddress = addresses.get(0);
                StringBuilder strAddress = new StringBuilder();
                for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
                    strAddress.append(fetchedAddress.getAddressLine(i)).append(" ");
                }

                Log.e("adddressssssss",strAddress.toString());

            } else {
                Log.e("errrrorororooror","Searching Current Address");
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("catchhhhh","Could not get address..!");
        }




//
//        URL url = new URL("https://www.mumbailive.com/en/civic/bmc-releases-a-new-list-of-containment-zones-or-red-zones-in-mumbai-as-of-june-1-50763.com");
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        try {
//            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            StringBuilder stringBuilder = new StringBuilder();
//            stringBuilder.append(in);
//           // readStream(in);
//        } finally {
//            urlConnection.disconnect();
//        }



        t.setText(result);


        //   tv.loadUrl("https://www.mumbailive.com/en/civic/bmc-releases-a-new-list-of-containment-zones-or-red-zones-in-mumbai-as-of-june-1-50763");

//        HttpClient httpClient=new DefaultHttp


        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Main.this, com.example.intent.setTime.class);
                //final PackageManager pm=getPackageManager();
//        List<ApplicationInfo> pack=pm.getInstalledApplications(PackageManager.GET_META_DATA);
//
////String a[]={"a","b","c","d"};
//        ArrayAdapter adapter=new ArrayAdapter<ApplicationInfo>(this,R.layout.list,pack);
//        ListView l=findViewById(R.id.listView);
//       // l.setAdapter(adapter);
//        for (ApplicationInfo packageInfo : pack){
//            Log.d(String.valueOf(this),"INSTALLED PACKAGES :" +packageInfo.packageName);
//            Log.d(String.valueOf(this),"SOURCE :" +packageInfo.sourceDir);
//            Log.d(String.valueOf(this),"LAUNCH ACTIVITY :" +pm.getLaunchIntentForPackage(packageInfo.packageName));
//           // Log.d(this,""+packageInfo.icon);
//        }

                startActivity(i);

            }
        });
    }
    private class ht extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("https://www.mumbailive.com/en/civic/bmc-releases-a-new-list-of-containment-zones-or-red-zones-in-mumbai-as-of-june-1-50763");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                    //StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    String a = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        // stringBuilder.append(line).append("\n");
                        a = String.format("%s%s", a, line);
                       // Log.e("testtttt", a);
                        //  tv.setText(line);
                    }
                    bufferedReader.close();
                    result = a;
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                result = e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            t.setText(Html.fromHtml(s));
        }
    }
    public void readWebpage(){
        ht task=new ht();
        task.execute();
    }

}