package com.example.graph25;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView a, bb, c, d;
    BarChart bar;

   public String data1;
    public String data2;
    public String data3;
    public String data4;

    /*public Float val;
        public Float val1;
        public Float val2;
        public Float val3;
        public Float val4;*/
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = findViewById(R.id.text1);
        c = findViewById(R.id.textView);
        d = findViewById(R.id.textView2);
        bb = findViewById(R.id.bb);
        bar = findViewById(R.id.bar1);


        ref = FirebaseDatabase.getInstance().getReference().child("graph");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*data1 = Integer.parseInt(dataSnapshot.child("Data1").getValue().toString());

                data2 = Integer.parseInt(dataSnapshot.child("Data2").getValue().toString());

                data3 = Integer.parseInt( dataSnapshot.child("Data3").getValue().toString());

                data4 = Integer.parseInt( dataSnapshot.child("Data4").getValue().toString());*/
                data1 = dataSnapshot.child("Data1").getValue().toString();

                data2 = dataSnapshot.child("Data2").getValue().toString();

                data3 = dataSnapshot.child("Data3").getValue().toString();

                data4 = dataSnapshot.child("Data4").getValue().toString();
                a.setText((data1));
                bb.setText(data2);
                c.setText(data3);
                d.setText(data4);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        int i=0 ;
        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(i+1, 12));
        barEntries.add(new BarEntry(i+2, 23));
        barEntries.add(new BarEntry(i+3,34));
        barEntries.add(new BarEntry(i+4, 45));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Data set");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(barDataSet);
        bar.setData(data);


    }

}
