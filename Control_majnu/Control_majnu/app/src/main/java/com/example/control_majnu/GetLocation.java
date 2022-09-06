package com.example.control_majnu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetLocation extends AppCompatActivity {
    CardView currLocation;
    Button otherLocation;
    private final static int PLACE_PICKER_REQUEST = 999;

    FusedLocationProviderClient fusedLocationProviderClient;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Location lastlocation;
    String SAddress;
    String Scity;
    PlacePicker.IntentBuilder intentBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
//       intentBuilder = new PlacePicker.IntentBuilder();

        Log.e("on cre","creaet");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        currLocation = (CardView)findViewById(R.id.curr_Location);
        otherLocation = (Button)findViewById(R.id.other_Location);
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(GetLocation.this);
//        sharedPreferences = getSharedPreferences("Categories" , Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
        new checkInternetConnection(this).checkConnection();

    }


    public void goPlacePicker(View view)
    {
        Log.e("picjerr","picker");
//        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        Log.e("picjerr","loaded");
        try
        { Log.e("tryy","tryyy");
//            PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
            Intent intent = intentBuilder.build(GetLocation.this);
            // Start the Intent by requesting a result, identified by a request code.
            startActivityForResult(intent, PLACE_PICKER_REQUEST);
//            startActivityForResult(builder.build(GetLocation.this) , PLACE_PICKER_REQUEST);
            Log.e("tryy","donee");
        }catch(GooglePlayServicesRepairableException e)
        {
            Log.e("eee",""+e);
        }catch(GooglePlayServicesNotAvailableException e)
        {
            Log.e("ERROR",""+e);

        }

    }
//------------------------------------Error expercted here
    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
       super.onActivityResult(requestCode, resultCode, data);
        Log.e("in ","searchhhhhh"+data);
        try {
            Log.e("in ","try");
            if (requestCode == PLACE_PICKER_REQUEST) {
                Log.e("in ","1"+resultCode);
//                if (resultCode == RESULT_OK) {
                    Log.e("in ","2");
                    Place place = PlacePicker.getPlace(GetLocation.this, data);
                    Geocoder geocoder = new Geocoder(this);
                    try {
                        List<Address> addresses = geocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1);
                        String address = addresses.get(0).getAddressLine(0);
                        //String city = addresses.get(0).getAddressLine();
                        String city1 = addresses.get(0).getSubAdminArea();
                        //String country = addresses.get(0).getAddressLine(2)
                        String knownName1 = addresses.get(0).getFeatureName();
                        String Locality = addresses.get(0).getLocality();
                        if (city1 != null) {
                            Scity = city1;
                        } else if (knownName1 != null) {
                            Scity = knownName1;
                        } else if (Locality != null) {
                            Scity = Locality;
                        }
                        editor.putString("City", Locality);
                        editor.putString("Address", address);
                        editor.commit();
                        //Toast.makeText(GetLocation.this , "AddressPlacePicker" + address , Toast.LENGTH_LONG).show();
                        //Toast.makeText(GetLocation.this , "CityPlacePicker" + Scity , Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(getApplicationContext(), AvailableWorkers.class);
                        startActivity(intent);

                    } catch (IOException e) {

                        Log.e("activiry err",""+e);
                        e.printStackTrace();
                    }
                    //tv.setText(place.getAddress());
                    //Toast.makeText(GetLocation.this, "Address" + place.getAddress() + "\n", Toast.LENGTH_SHORT).show();
//                }
            }
        }
        catch (Exception e){
            Log.e("picker" ,""+e);
        }

    }

public void mapp(View view){

    Intent i = new Intent(this,MapsActivity.class);
    startActivity(i);
}

    public void dothis(View v)
    {

        Log.e("picjerr","in doooo");
        getlocation();
    }

    void getlocation()
    {

        Log.e("getloccc","in locccc");
        if(ActivityCompat.checkSelfPermission(this , android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this , new String[]{Manifest.permission.ACCESS_FINE_LOCATION} , 1);

        }
        else
        {
            Log.e("TAG" , "getLocation: permissions granted");
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>()
            {
                @Override
                public void onSuccess(Location location)
                {

                    if(location != null)
                    {

                        Log.e("succ","loaccc");
                        lastlocation = location;
                        double latitude = lastlocation.getLatitude();
                        double longitue = lastlocation.getLongitude();
Log.e("",""+latitude+":"+longitue);
                        //tvlatitue.setText(""+latitude);
                        //tvlongitude.setText(""+longitue);
                        Geocoder geocoder = new Geocoder(GetLocation.this , Locale.getDefault());


                        try
                        {
                            //List<Address> locationlist = geocoder.getFromLocation(latitude,longitue,1);
                            List<Address> addresses = geocoder.getFromLocation(latitude , longitue , 1);

                            //Toast.makeText(GetLocation.this , "Inside try" , Toast.LENGTH_SHORT).show();

                            if(addresses.size() > 0)
                            {
                                // Address address = locationlist.get(0);
                                String address1 = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                String city1 = addresses.get(0).getSubAdminArea();
                                String state1 = addresses.get(0).getAdminArea();
                                String country1 = addresses.get(0).getCountryName();
                                String postalCode1 = addresses.get(0).getPostalCode();
                                String knownName1 = addresses.get(0).getFeatureName();
                                String Locality=addresses.get(0).getLocality();
                                /**/
                                if(city1!=null)
                                {
                                    Scity=city1;
                                }
                                else if(knownName1!=null)
                                {
                                    Scity=knownName1;
                                }
                                else if(Locality!=null)
                                {
                                    Scity=Locality;
                                }

                                editor.putString("City" , Scity);
                                editor.putString("Address",address1);
                                editor.commit();

                                //Toast.makeText(GetLocation.this , "Address : " + address1 , Toast.LENGTH_SHORT).show();
                                //Toast.makeText(GetLocation.this , "City : " + Scity , Toast.LENGTH_SHORT).show();
                                Log.e("Checking",Scity);

                                startActivity(new Intent(GetLocation.this , AvailableWorkers.class));

                                // tvphysicaladdress.setText("Address is:"+ address);
                            }

                        }catch(IOException e)
                        {

                            Log.e("eeeeee","eeeee"+e);
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode , @NonNull String[] permissions , @NonNull int[] grantResults)
    {
        //super.onRequestPermissionsResult(requestCode , permissions , grantResults);
        switch(requestCode)
        {
            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    getlocation();
                }
                else
                {
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
                }
        }
    }
}