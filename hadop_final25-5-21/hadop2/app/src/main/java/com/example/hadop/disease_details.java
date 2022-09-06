package com.example.hadop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class disease_details extends AppCompatActivity {
    ListView ll;
    TextView disea,da,ti;
    Button detai;
    Bundle b;
    String date,symp,diseaaa,timeee;
    ArrayAdapter a;
    String[] lis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_details);
        b=getIntent().getExtras();
        ll=findViewById(R.id.past_symptoms_list_details);
        disea=findViewById(R.id.disease_details);
        detai=findViewById(R.id.nex_details);
        da=findViewById(R.id.date_detail);
        ti=findViewById(R.id.time_de);
        try {
            date ="Date : "+ b.getString("date");
            symp = b.getString("symptomps");
            timeee ="Time : "+ b.getString("time");
            lis=symp.split(",");
            Log.e("lissss",""+lis.length);
            diseaaa = b.getString("disease");
            da.setText(date);
            disea.setText(diseaaa);
            Log.e("time","0"+timeee);
            ti.setText(timeee);
            a=new ArrayAdapter(this,R.layout.sympto_item,lis);
            ll.setAdapter(a);
            detai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextt(diseaaa);
                }
            });
        }
        catch(Exception e){
            Log.e("ERROR",""+e);
        }

    }
    void nextt(String value){
        Intent i = new Intent(disease_details.this,webv.class);
        i.putExtra("disease",value);
        startActivity(i);
    }
}