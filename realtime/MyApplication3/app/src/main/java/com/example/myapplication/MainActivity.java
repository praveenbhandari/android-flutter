package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
Button b;
String soem="something";
FirebaseDatabase ff=FirebaseDatabase.getInstance();
DatabaseReference rrr= ff.getReference("ttest");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.but);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    rrr.setValue(soem);
                    Log.e("dataa","set");

                }
                catch(Exception e){
                    Log.e("dataa","errot  "+e);

                }
            }
        });



    }



}