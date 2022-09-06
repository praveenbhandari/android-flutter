package com.example.graph25;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private static final int PERMISSION_REQUEST_CODE = 1;
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
    BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("SERVICE SENT", "LETS HOPE FOR BEST");
        // Intent i=;
        startService(new Intent(this, servi.class));

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }
        Log.e("GETTING DATA","LETS HOPE");
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
            //    Bundle b = intent.getExtras();
                data1= intent.getExtras().get("data1").toString();
                data2= intent.getExtras().get("data2").toString();
                data3= intent.getExtras().get("data3").toString();
                data4= intent.getExtras().get("data4").toString();
             //   data4 = b.getString("data4");
                Log.e("MainActivity", "data 1 = " + data1);

                Log.e("MainActivity", "data 2= " + data2);

                Log.e("MainActivity", "data 3= " + data3);

                Log.e("MainActivity", "data 4= " + data4);
                a.setText(data1);
                bb.setText(data2);
                c.setText(data3);
                d.setText(data4);
                  textmess();
                   issueNotification();
            }

        };

        registerReceiver(br, new IntentFilter("INTENT"));
        //getdataformservice();
        Log.e("RUNNED","gedata()");
        a = findViewById(R.id.text1);
        c = findViewById(R.id.textView);
        d = findViewById(R.id.textView2);
        bb = findViewById(R.id.bb);
        bar = findViewById(R.id.bar1);


//        ref = FirebaseDatabase.getInstance().getReference().child("graph");
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                /*data1 = Integer.parseInt(dataSnapshot.child("Data1").getValue().toString());
//
//                data2 = Integer.parseInt(dataSnapshot.child("Data2").getValue().toString());
//
//                data3 = Integer.parseInt( dataSnapshot.child("Data3").getValue().toString());
//
//                data4 = Integer.parseInt( dataSnapshot.child("Data4").getValue().toString());*/
//                data1 = dataSnapshot.child("1").getValue().toString();
//
//                data2 = dataSnapshot.child("Data2").getValue().toString();
//
//                data3 = dataSnapshot.child("Data3").getValue().toString();
//
//                data4 = dataSnapshot.child("Data4").getValue().toString();
//
//
//                textmess();
//issueNotification();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });

        // int i=0 ;
        // l=Integer.parseInt(data1);
        //u=Integer.parseInt(data2);
        a.setText((data1));
        bb.setText(data2);
        c.setText(data3);
        d.setText(data4);
        barEntries.add(new BarEntry(1, 22));
        barEntries.add(new BarEntry(2, 55));

        barEntries.add(new BarEntry(3, 34));
        barEntries.add(new BarEntry(4, 45));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Data set");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(barDataSet);
        bar.setData(data);

//getdataformservice();
    }

    public void getdataformservice() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void makeNotificationChannel(String id, String name, int importance) {
        NotificationChannel channel = new NotificationChannel(id, name, importance);
        channel.setShowBadge(true); // set false to disable badges, Oreo exclusive

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);
    }

    public void issueNotification() {

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
                .setContentTitle("WARNING")
                .setContentText("Drowsines Detected!")
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setNumber(3); // this shows a number in the notification dots

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(1, notification.build());
        // it is better to not use 0 as notification id, so used 1.
    }


    public void textmess() {
        if (checkPermission()) {

//Get the default SmsManager//

            SmsManager smsManager = SmsManager.getDefault();

//Send the SMS//

            smsManager.sendTextMessage("9619984854", null, "YOU MUST SLEEP", null, null);
        } else {
            Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);

    }


}






