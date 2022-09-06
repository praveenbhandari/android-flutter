package com.example.mongodb_realm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class userdata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);
        TextView tv= findViewById(R.id.text);
        Intent i =getIntent();
        String data = i.getExtras().get("data").toString();
        tv.setText(data);
    }
}