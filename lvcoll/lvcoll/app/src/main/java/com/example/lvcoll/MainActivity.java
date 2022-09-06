package com.example.lvcoll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView textView;
ListView listView;
String[] data=new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",};
//StringBuffer sb=new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        listView=findViewById(R.id.listview);
//        for(int i=0;i<100;i++){
//            data[i]= String.valueOf(i);
//            sb.append(data[i]);
//            Log.e("data","data"+i);
//        }
        final ArrayAdapter a= new ArrayAdapter(this,R.layout.listitem,R.id.textView,data);
        listView.setAdapter(a);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"You Selected  "+data[i], Toast.LENGTH_SHORT).show();
            }
        });
    }
}