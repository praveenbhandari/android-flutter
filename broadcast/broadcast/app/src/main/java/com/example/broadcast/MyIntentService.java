package com.example.broadcast;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.Date;

public class MyIntentService extends IntentService {

    public static final String CUSTOM_ACTION = "ACTION_AIRPLANE_MODE_CHANGED ";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent arg0) {
        Intent intent = new Intent(CUSTOM_ACTION);
        intent.putExtra("DATE", new Date().toString());
        Log.d(MyIntentService.class.getSimpleName(), "sending broadcast");

        // send local broadcast
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}