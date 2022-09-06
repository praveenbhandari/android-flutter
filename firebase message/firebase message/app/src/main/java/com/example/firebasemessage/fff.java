package com.example.firebasemessage;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

class fff extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {

        Log.e("TOken =",""+s);
        super.onNewToken(s);
        Log.e("TOken1 =",""+s);
        Toast.makeText(fff.this,s,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Toast.makeText(fff.this,"RECEIVED",Toast.LENGTH_SHORT).show();

        super.onMessageReceived(remoteMessage);
        Toast.makeText(fff.this,"RECEIVED",Toast.LENGTH_SHORT).show();

    }
}
