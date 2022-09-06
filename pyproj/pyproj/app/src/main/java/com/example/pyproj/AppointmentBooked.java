package com.example.pyproj;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AppointmentBooked extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_booked);

        t1 = findViewById(R.id.textView1);
        t2 = findViewById(R.id.textView2);
        t3 = findViewById(R.id.textView3);
        t4 = findViewById(R.id.textView4);
        t5 = findViewById(R.id.textView5);
        t6 = findViewById(R.id.textView6);
        t7 = findViewById(R.id.textView7);
        t8 = findViewById(R.id.textView8);
        t9 = findViewById(R.id.textView9);
        t21 = findViewById(R.id.textView21);

        t1.setText(getIntent().getStringExtra("DATE"));
        t2.setText(getIntent().getStringExtra("TIME"));
        t3.setText(getIntent().getStringExtra("SYMPTOMS"));
        t4.setText(getIntent().getStringExtra("BLOOD"));
        t5.setText(getIntent().getStringExtra("NAME"));
        t6.setText(getIntent().getStringExtra("AGE"));
        t7.setText(getIntent().getStringExtra("EMAIL"));
        t8.setText(getIntent().getStringExtra("PHONE"));
        t9.setText(getIntent().getStringExtra("DOCTOR"));
        t21.setText(getIntent().getStringExtra("GENDER"));

    }
}