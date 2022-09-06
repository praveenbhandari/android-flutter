package com.example.graph25;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

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
    ArrayList<BarEntry> barEntries = new ArrayList<>();
    int l, u;
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
issueNotification();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        // int i=0 ;
        // l=Integer.parseInt(data1);
        //u=Integer.parseInt(data2);
        barEntries.add(new BarEntry(1, 22));
        barEntries.add(new BarEntry(2, 55));

        barEntries.add(new BarEntry(3, 34));
        barEntries.add(new BarEntry(4, 45));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Data set");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(barDataSet);
        bar.setData(data);


    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    void makeNotificationChannel(String id, String name, int importance)
    {
        NotificationChannel channel = new NotificationChannel(id, name, importance);
        channel.setShowBadge(true); // set false to disable badges, Oreo exclusive

        NotificationManager notificationManager =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);
    }
    void issueNotification()
    {

        // make the channel. The method has been discussed before.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            makeNotificationChannel("CHANNEL_1", "Example channel", NotificationManager.IMPORTANCE_DEFAULT);
        }
        // the check ensures that the channel will only be made
        // if the device is running Android 8+

        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this, "CHANNEL_1");
        // the second parameter is the channel id.
        // it should be the same as passed to the makeNotificationChannel() method

        notification
                .setSmallIcon(R.mipmap.ic_launcher) // can use any other icon
                .setContentTitle("soojaoooooo!")
                .setContentText("bitchhhhhhhhh")
                .setNumber(3); // this shows a number in the notification dots

        NotificationManager notificationManager =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(1, notification.build());
        // it is better to not use 0 as notification id, so used 1.
    }
}



