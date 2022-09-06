package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mDateText;
    Button mStartButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDateText = (TextView) findViewById(R.id.dateText);
        mStartButton = findViewById(R.id.startButton);

        mStartButton.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // unregister local broadcast
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register local broadcast
        IntentFilter filter = new IntentFilter(MyIntentService.CUSTOM_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, filter);
    }

    /**
     * Broadcast receiver to receive the data
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String date = intent.getStringExtra("DATE");
            mDateText.setText(date);
        }
    };

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.startButton) {
            // start intent service
            Intent intent = new Intent(this, MyIntentService.class);
            startService(intent);
        }
    }
}