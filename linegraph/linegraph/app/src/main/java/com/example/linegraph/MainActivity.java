package com.example.linegraph;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

public class MainActivity extends AppCompatActivity {
    EditText xvalue;
    EditText yvalue;
    Button btn_insert;


    FirebaseDatabase database;
    DatabaseReference reference;
    public GraphView graphView;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xvalue = (EditText) findViewById(R.id.editText);
        yvalue = (EditText) findViewById(R.id.editText2);
        btn_insert = (Button) findViewById(R.id.button);
        graphView = (GraphView) findViewById(R.id.graphView);
        Series series = new LineGraphSeries();
        graphView.addSeries(series);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("chartTable");

        setListeners();
    }

    private void setListeners() {
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = reference.push().getKey();

                int x = Integer.parseInt(xvalue.getText().toString());
                int y = Integer.parseInt(yvalue.getText().toString());

                pointvalue pointvalue = new pointvalue(x, y);

                reference.child("chartTable").setValue(pointvalue);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataPoint[] dp = new DataPoint[(int) dataSnapshot.getChildrenCount()];
                int index = 0;

                for (DataSnapshot myDtaSnapshot : dataSnapshot.getChildren()) {

                    pointvalue pointvalue = myDtaSnapshot.getValue(pointvalue.class);
                    dp[index] = new DataPoint(pointvalue.getxvalue(), pointvalue.getyvalue());
                    index++;
                }

                //series.resetData(dp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
