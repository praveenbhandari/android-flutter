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
import android.provider.Settings;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

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

public class MainActivity extends Activity {
    private static final int PERMISSION_REQUEST_CODE = 1;
  //  TextView a, bb, c, d;
    EditText number;
    Button but;
    BarChart bar;
 //   ArrayList<BarEntry> barEntries = new ArrayList<>();
    ArrayList<BarEntry> da = new ArrayList<>();
    public String data1;
    public String data2;
    public String data3;
    public String data4,data5,data6,data7;
    BroadcastReceiver br;
    String phone;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myR = database.getReference("graph/Phone");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
number=findViewById(R.id.number);
but=findViewById(R.id.button);
        bar = findViewById(R.id.bar1);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String num=number.getText().toString();

                myR.setValue(num);
                myR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("Phone no is ", "" + value);
                        phone=value;
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("TAG", "Failed to read value.", error.toException());
                    }
                });
            }
        });
        // Read from the database
        myR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Phone no is ", "" + value);
                phone=value;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
        Log.e("SERVICE SENT", "LETS HOPE FOR BEST");

        startService(new Intent(this, servi.class));

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }
        Log.e("GETTING DATA", "LETS HOPE");
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //    Bundle b = intent.getExtras();

                data1 = intent.getExtras().get("data1").toString();
                data2 = intent.getExtras().get("data2").toString();
                data3 = intent.getExtras().get("data3").toString();
                data4 = intent.getExtras().get("data4").toString();
                data5 = intent.getExtras().get("data5").toString();
                data6 = intent.getExtras().get("data6").toString();
                data7 = intent.getExtras().get("data7").toString();
                en(data1, data2, data3, data4,data5,data6,data7);
                //   data4 = b.getString("data4");
                Log.e("MainActivity", "data 1 = " + data1);

                Log.e("MainActivity", "data 2= " + data2);

                Log.e("MainActivity", "data 3= " + data3);

                Log.e("MainActivity", "data 4= " + data4);
//                a.setText(data1);
//                bb.setText(data2);
//                c.setText(data3);
//                d.setText(data4);


                textmess();
                issueNotification();
            }

        };

        registerReceiver(br, new IntentFilter("INTENT"));
        //getdataformservice();
        Log.e("RUNNED", "gedata()");
//        a = findViewById(R.id.text1);
//        c = findViewById(R.id.textView);
//        d = findViewById(R.id.textView2);
//        bb = findViewById(R.id.bb);



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
//        a.setText(data1);
//        bb.setText(data2);
//        c.setText(data3);
//        d.setText(data4);
//        barEntries.add(new BarEntry(1, 22));
//        barEntries.add(new BarEntry(2, 55));
//
//        barEntries.add(new BarEntry(3, 34));
//        barEntries.add(new BarEntry(4, 45));


    }

    void en(String a, String b, String c, String d,String e,String f,String g) {
        int aa, bb, cc, dd,ee,ff,gg;
        aa = Integer.parseInt(a);
        bb = Integer.parseInt(b);
        cc = Integer.parseInt(c);
        dd = Integer.parseInt(d);
        ee = Integer.parseInt(e);
        ff = Integer.parseInt(f);
        gg = Integer.parseInt(g);
       da.clear();
        da.add(new BarEntry(1, aa));
        da.add(new BarEntry(2, bb));
        da.add(new BarEntry(3, cc));
        da.add(new BarEntry(4, dd));
        da.add(new BarEntry(5, ee));
        da.add(new BarEntry(6, ff));
        da.add(new BarEntry(7, gg));
        for (int i = 0; i < da.size(); i++) {
            Log.e("LIST DATA in en", String.valueOf(da.get(i)));
            //da.add(new BarEntry(i+1, i));
        }
        BarDataSet barDataSet = new BarDataSet(da,"STATS");
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        BarData data = new BarData(barDataSet);
        bar.setData(data);

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

            smsManager.sendTextMessage(phone, null, "YOU MUST TAKE SOME REST", null, null);
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






