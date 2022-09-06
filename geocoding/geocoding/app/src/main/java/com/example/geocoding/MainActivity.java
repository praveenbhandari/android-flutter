package com.example.geocoding;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
Button getaddress;
TextView op;
EditText ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getaddress=findViewById(R.id.getaddr);
        ip=findViewById(R.id.addr);
        op=findViewById(R.id.txt);

        getaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input= ip.getText().toString();

                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.ENGLISH);
                try {
                    List<Address> addresses = geocoder.getFromLocationName(input, 3);
                    if (addresses.size() > 0) {
                        Address fetchedAddress = addresses.get(0);
                        double lat =  fetchedAddress.getLatitude();
                        double lon = fetchedAddress.getLongitude();
                        StringBuilder strAddress = new StringBuilder();
                        for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
                            strAddress.append(fetchedAddress.getAddressLine(i));
                        }
                        String latlng="lat="+lat+"   long="+lon;
                        Log.e("adddressssssss",latlng);
                        op.setText(latlng);
                        Log.e("adddressssssss",strAddress.toString());

                    } else {
                        op.setText("SEARCHING FOR ADdRESS");
                        Log.e("errrrorororooror","Searching Current Address");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    op.setText("could not get adress");
                    Log.e("catchhhhh","Could not get address..!");
                }

            }
        });
    }
}