package com.example.linechart29;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText xValue, yValue;
    Button insert;
    LineChart lineChart;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    LineDataSet lineDataSet=new LineDataSet(null,null);
    ArrayList<LineDataSet> iLineDataSets=new ArrayList<>();

    LineData LineData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue= findViewById(R.id.editText);
        yValue= findViewById(R.id.editText2);
        insert= findViewById(R.id.button);
        lineChart= findViewById(R.id.LineChartView);
        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference("chartTable");
        insertData();


    }

    private void insertData() {
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=myRef.push().getKey();
                int x= Integer.parseInt(xValue.getText().toString());
                int y= Integer.parseInt(yValue.getText().toString());
                DataPoint dataPoint=new DataPoint(x,y);
                myRef.child(id).setValue(dataPoint);
                retrivedata();
            }
        });
    }

    private void retrivedata() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> dataVals= new ArrayList<>();

                if(dataSnapshot.hasChildren()){
                    for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){
                        DataPoint dataPoint=myDataSnapshot.getValue(DataPoint.class);
                        dataVals.add(new Entry(dataPoint.getxValue(),dataPoint.getyValue()));
                    }
                    showChart(dataVals);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showChart(ArrayList<Entry> dataVals) {
        lineDataSet.setValues(dataVals);
        lineDataSet.setLabel("Dataset 1");
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        LineData = new LineData((ILineDataSet) iLineDataSets);
        lineChart.clear();
        lineChart.setData(LineData);
        lineChart.invalidate();

    }
}
