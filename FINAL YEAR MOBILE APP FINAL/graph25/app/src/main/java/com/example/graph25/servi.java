package com.example.graph25;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class servi extends Service {
    DatabaseReference ref;
    public String data1;
    public String data2;
    public String data3;
    public String data4,data5,data6,data7;

    Intent i;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.e("servie","created");
        Toast.makeText(this,"Service Created",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart(Intent intent, int startId)  {
        Log.e("servi ","at STARTED");
        ref = FirebaseDatabase.getInstance().getReference().child("graph");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data1 = dataSnapshot.child("1").getValue().toString();
                data2 = dataSnapshot.child("Data2").getValue().toString();
                data3 = dataSnapshot.child("Data3").getValue().toString();
                data4 = dataSnapshot.child("Data4").getValue().toString();
                data5 = dataSnapshot.child("Data5").getValue().toString();
                data6 = dataSnapshot.child("Data6").getValue().toString();
                data7 = dataSnapshot.child("Data7").getValue().toString();
                Log.e("data1",""+data1);
                Log.e("data2",""+data2);
                Log.e("data3",""+data3);
                Log.e("data4",""+data4);
                Log.e("data5",""+data5);
                Log.e("data6",""+data6);
                Log.e("data7",""+data7);
//                a.setText((data1));
//                bb.setText(data2);
//                c.setText(data3);
//                d.setText(data4);
                  textmess();
                   issueNotification();
               // inten();


                i= new Intent("INTENT");
                i.putExtra("data1",data1);
                i.putExtra("data2",data2);
                i.putExtra("data3",data3);
                i.putExtra("data4",data4);
                i.putExtra("data5",data5);
                i.putExtra("data6",data6);
                i.putExtra("data7",data7);


                sendBroadcast(i);
                Log.e("INTENT","INTENT SENT INSIDE DATACHANGED");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
//        i.putExtra("data1",data1);
//        i.putExtra("data2",data2);
//        i.putExtra("data3",data3);
//        i.putExtra("data4",data4);
//        i.setAction("INTENT");
//        sendBroadcast(i);
        Log.e("INTENT","INTENT SENT OUTSIDE DATACHANGED");
        Log.e("servie","Started");
        Toast.makeText(this,"Service Started",Toast.LENGTH_SHORT).show();



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
      //  Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
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
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(servi.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public void textmess() {
        if (checkPermission()) {

//Get the default SmsManager//

            SmsManager smsManager = SmsManager.getDefault();

//Send the SMS//

            smsManager.sendTextMessage("9619984854", null, "YOU MUST SLEEP", null, null);
        } else {
            Toast.makeText(servi.this, "Permission denied", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
