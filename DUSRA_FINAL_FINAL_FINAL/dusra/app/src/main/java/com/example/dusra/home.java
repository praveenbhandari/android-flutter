package com.example.dusra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import io.opencensus.common.Function;

public class home extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    Button kyc=findViewById(R.id.kyc);











// Add a new document with a generated ID








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
    Button pack1=findViewById(R.id.packageinfo);
    pack1.setOnClickListener(new View.OnClickListener() {
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
