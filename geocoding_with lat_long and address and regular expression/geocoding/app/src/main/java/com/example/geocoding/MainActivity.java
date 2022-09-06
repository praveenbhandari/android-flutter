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
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
Button getaddress;
TextView op;
EditText ip;
String opaddress;
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




                if(input.isEmpty()){Toast.makeText(MainActivity.this,"Give some Input",Toast.LENGTH_LONG).show();}
                else if(input.matches("[-]*[0-9]{2}.[0-9]{1,15}[,][-]*[0-9]{2}.[0-9]{1,15}")){
                    lat(input);
                    Toast.makeText(MainActivity.this,"Searching for Latitude and Longitude",Toast.LENGTH_LONG).show();
                    Log.e("","Number");
                }
                else{
                    address(input);
                    Toast.makeText(MainActivity.this,"Searching for address",Toast.LENGTH_LONG).show();
                    Log.e("","else");
                }


            }
        });


    }

    public  void  lat(String input){

        String latlng[]=input.split(",");

                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.ENGLISH);
                try {
                    List<Address> addresses = geocoder.getFromLocation(Float.parseFloat(latlng[0]),Float.parseFloat(latlng[1]), 1);

                    if (addresses.size() > 0) {


                        Address fetchedAddress = addresses.get(0);
                        opaddress=fetchedAddress.getAddressLine(0);
                        Log.e("a",""+opaddress);

                        op.setText(opaddress);
                        Toast.makeText(MainActivity.this,"Address is: "+opaddress,Toast.LENGTH_LONG).show();
                        Log.e("adddressssssss",""+opaddress);

                    } else {
                        Toast.makeText(MainActivity.this,"Error getting address",Toast.LENGTH_LONG).show();
                        op.setText("ERROR GETTING ADDRESS");
                        Log.e("errrrorororooror","Searching Current Address");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    op.setText("ERROR GETTING ADDRESS");
                    Toast.makeText(MainActivity.this,"Error getting address",Toast.LENGTH_LONG).show();
                    Log.e("catchhhhh","Could not get address..!");
                }



    }
    public void address(String input){
        Geocoder geocoder1 = new Geocoder(MainActivity.this, Locale.ENGLISH);
        try {
            List<Address> addresses = geocoder1.getFromLocationName(input, 3);
            if (addresses.size() > 0) {
                Address fetchedAddress = addresses.get(0);
                double lat =  fetchedAddress.getLatitude();
                double lon = fetchedAddress.getLongitude();
                StringBuilder strAddress = new StringBuilder();
                for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
                    strAddress.append(fetchedAddress.getAddressLine(i));
                }
                String latlng1="lat="+lat+"   long="+lon;
                Log.e("adddressssssss",latlng1);
                op.setText(latlng1);
                Toast.makeText(MainActivity.this,"LatLng is: "+latlng1,Toast.LENGTH_LONG).show();
                Log.e("adddressssssss",strAddress.toString());

            } else {
                op.setText("SEARCHING FOR ADDRESS");
                Toast.makeText(MainActivity.this,"Error getting address",Toast.LENGTH_LONG).show();
                Log.e("errrrorororooror","Searching Current Address");
            }

        } catch (IOException e) {
            e.printStackTrace();
            op.setText("could not get address");
            Toast.makeText(MainActivity.this,"Error getting address",Toast.LENGTH_LONG).show();
            Log.e("catchhhhh","Could not get address..!");
        }
    }

}