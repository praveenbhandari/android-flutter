package com.example.dusra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    Button kyc=findViewById(R.id.kyc);
    kyc.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent btnL = new Intent(getApplicationContext(),kyc.class);
        startActivity(btnL);
      }
    });
    Button subsc=findViewById(R.id.subcription);
    subsc.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent btnL = new Intent(getApplicationContext(),subscriction.class);
        startActivity(btnL);
      }
    });
    Button data=findViewById(R.id.datausage);
    data.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent btnL = new Intent(getApplicationContext(),datausage.class);
        startActivity(btnL);
      }
    });
    Button pack=findViewById(R.id.packageinfo);
    pack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent btnL = new Intent(getApplicationContext(),packageinfo.class);
        startActivity(btnL);
      }
    });
    Button dlimit=findViewById(R.id.datalimit);
    dlimit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent btnL = new Intent(getApplicationContext(),datalimit.class);
        startActivity(btnL);
      }
    });
    Button aboutus=findViewById(R.id.aboutus);
    aboutus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent btnL = new Intent(getApplicationContext(),aboutus.class);
        startActivity(btnL);
      }
    });
  }
}
