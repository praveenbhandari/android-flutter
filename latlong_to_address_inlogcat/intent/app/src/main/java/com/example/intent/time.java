package com.example.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class time extends AppCompatActivity {
TextView date,time;  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        time=findViewById(R.id.time);
        date=findViewById(R.id.date);

      Bundle extras=  getIntent().getExtras();
      if(extras == null ){
          Toast.makeText(this,"NO date received",Toast.LENGTH_LONG).show();
      }else{
      String date1=extras.getString("Date");
      String time1=extras.getString("Time");
      date.setText(date1);
      time.setText(time1);
      }

    }
}