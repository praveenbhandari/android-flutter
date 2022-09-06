package com.example.hadop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class precaution extends AppCompatActivity {
    TextView disease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precaution);
        disease=findViewById(R.id.disease);
        Bundle b=getIntent().getExtras();
       String dise=b.getString("disease");
       disease.setText(dise);

    }
}