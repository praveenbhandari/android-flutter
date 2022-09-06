package com.example.intent;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class Main extends AppCompatActivity {
Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button setTime=findViewById(R.id.setTime);


        final PackageManager pm=getPackageManager();
        List<ApplicationInfo> pack=pm.getInstalledApplications(PackageManager.GET_META_DATA);

///String a[]={"a","b","c","d"};
        ArrayAdapter adapter=new ArrayAdapter<ApplicationInfo>(this,R.layout.list,pack);
        ListView l=findViewById(R.id.listView);
        l.setAdapter(adapter);
        for (ApplicationInfo packageInfo : pack){












            Log.d(String.valueOf(this),"INSTALLED PACKAGES :" +packageInfo.packageName);
            Log.d(String.valueOf(this),"SOURCE :" +packageInfo.sourceDir);
            Log.d(String.valueOf(this),"LAUNCH ACTIVITY :" +pm.getLaunchIntentForPackage(packageInfo.packageName));
           // Log.d(this,""+packageInfo.icon);

        }



        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this, com.example.intent.setTime.class);
                startActivity(i);
            }
        });
    }
}