package com.example.geofencing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.but);

        b.setOnClickListener(new View.OnClickListener(){
           public  void onClick(View v){
                 Intent i = new Intent(MainActivity2.this,MainActivity.class);
                 startActivity(i);
        }
        });
    }
}