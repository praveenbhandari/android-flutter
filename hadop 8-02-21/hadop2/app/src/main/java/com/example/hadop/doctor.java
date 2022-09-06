package com.example.hadop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class doctor extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView nav;
    FloatingActionButton fab;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference ref = firebaseFirestore.collection("data");
    String scn_data = "";
    String doc_name;
    TextView t, display_location;
    View parentLayout;
    Button doc_loc;
    Map<String, Object> data = new HashMap<>();
    //    ArrayAdapter a;
//    String[] aas={"asd","asdads","asdasdadad","asd","asdads","asdasdadad","asd","asdads","asdasdadad","asd","asdads","asdasdadad","asd","asdads","asdasdadad","asd","asdads","asdasdadad",};
    ListView listView;

    String final_address;

    CustomList customList;
    ArrayList<Custom_list_data> arrayList = new ArrayList<>();
    ArrayList<String> recent_uid = new ArrayList<>();

    //    ArrayList<String> symp=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        fab = findViewById(R.id.fab_doc);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        doc_loc = findViewById(R.id.doc_loc);
        drawer = findViewById(R.id.drawer);
        nav = findViewById(R.id.navigation_view);
        nav.setNavigationItemSelectedListener(this);
//        t = findViewById(R.id.doc_text);
        parentLayout = findViewById(android.R.id.content);
        display_location = findViewById(R.id.your_loca);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//doc_loc.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        Intent
//    }
//});
        listView = findViewById(R.id.list_view);
//        arrayList.add(new Custom_list_data("1", " asdad","cough","12222"));
//        arrayList.add(new Custom_list_data( "2"," asdasd","cold","12333"));
//        arrayList.add(new Custom_list_data( "3"," adfg","fever","1300"));
        customList = new CustomList(this, arrayList);
//        listView.setAdapter(customList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              display.snakbar(view, String.valueOf(position+1));
//                Intent i = new Intent(doctor.this,patient_details_to_doc.class);
                get_patient_data(recent_uid.get(position));
//                startActivity(i);
                Toast.makeText(doctor.this, "clicked on " + position, Toast.LENGTH_SHORT).show();
            }
        });
        Log.e("getting", "recent patients");
        recent_patients();
        Log.e("done getting", "recent patients");
        ref.document(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.e("TAG", "DocumentSnapshot data: " + document.getString("name"));
                        doc_name = document.getString("name");
//                        t.setText("Doctor Name: " + doc_name);
                        Snackbar.make(parentLayout, "Welcome Doctor " + doc_name, Snackbar.LENGTH_SHORT).setBackgroundTint(Color.YELLOW).setActionTextColor(Color.BLACK).show();
                        getSupportActionBar().setTitle("Doctor Name: " + doc_name);
                    } else {
                        Log.e("TAG", "No such document");
                    }

                }
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator integrator = new IntentIntegrator(doctor.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });


    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.e("itemmmm", "" + item.getTitle());
        Log.e("itemmmm", "" + item.getItemId());
        Toast.makeText(this, "" + item.getTitle(), Toast.LENGTH_SHORT).show();
        Log.e("trying", "to change");
//        if (item.getTitle() == "somwthing 1") {
//            getSupportFragmentManager().beginTransaction().replace(R.id.frag, new frag_test1()).commit();
//        } else if (item.getTitle() == "somwthing 2") {
//            getSupportFragmentManager().beginTransaction().replace(R.id.frag, new frag_test()).commit();
//        }
        Log.e("done", "to change");

        drawer.closeDrawer(GravityCompat.START);
        switch (item.getTitle().toString()) {
            case "Logout":
                signout();
                break;
            case "someasd":
                break;

            default:
                Log.e("switch", "" + item.getTitle());
                break;
        }
        return true;

    }

    void signout() {
        Log.e("in signout", " signout");

        try {
            Toast.makeText(this, "Signout Successful", Toast.LENGTH_SHORT).show();
            Log.e("in signout", "starting signout");
            FirebaseAuth.getInstance().signOut();
            Log.e("in signout", "done signout");
            Intent i = new Intent(doctor.this, MainActivity.class);
            startActivity(i);
        } catch (Exception e) {
            Snackbar.make(parentLayout, "ERROR SIGNOUT", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.e("Scan*******", "NULLL");

            } else {
                Log.e("Scan", "Scanned");
                Log.e("result", result.getContents());
//                tv_qr_readTxt.setText(result.getContents());
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                scn_data = result.getContents();

                get_patient_data(result.getContents());

            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    void get_patient_data(String patient_uid) {
        ref.document(patient_uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.e("geting data ", "of patinet");
                        String p_name = document.get("name").toString();


                        String p_age = document.get("age").toString();
                        String p_phone = document.get("phone").toString();
//                        Log.e("data",p_age+""+p_phone);
                        String p_gender = document.get("gender").toString();
                        String p_uid = document.get("uid").toString();
                        String p_loc = document.get("location").toString();
                        Log.e("data", p_uid + "" + p_name + "" + p_age + "" + p_gender + "" + p_loc + "" + p_phone);


                        Intent i = new Intent(doctor.this, patient_details_to_doc.class);
                        i.putExtra("uid", p_uid);
                        i.putExtra("name", p_name);
                        i.putExtra("age", p_age);
                        i.putExtra("phone", p_phone);
                        i.putExtra("gender", p_gender);
                        i.putExtra("doc_patient_channel", auth.getUid() + "-" + p_uid);
                        i.putExtra("patient_location", p_loc);
                        startActivity(i);


                    }

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("ERROR", "getting patient detials");
            }
        });
    }

    void recent_patients() {
        ref.document(auth.getUid()).collection("patient history").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot queryDocumentSnapshots = task.getResult();
                    Log.e("getting", "recent patients" + queryDocumentSnapshots.getDocuments().size());


                    for (int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++) {
                        Log.e("data", queryDocumentSnapshots.getDocuments().get(i).get("name").toString());
                        String name = queryDocumentSnapshots.getDocuments().get(i).get("name").toString();
//                        String date=queryDocumentSnapshots.getDocuments().get(i).get("age").toString();
                        String p_uid = queryDocumentSnapshots.getDocuments().get(i).get("uid").toString();
                        Log.e("data", "adding in list");
                        arrayList.add(new Custom_list_data(String.valueOf(i + 1), name, "some", "date"));
                        recent_uid.add(p_uid);
//                        symp.add(queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
                    }
//                    listView.setAdapter(a);
                    Log.e("data", "adding in list" + arrayList);
                    customList = new CustomList(doctor.this, arrayList);
                    listView.setAdapter(customList);
                } else {
                    Toast.makeText(doctor.this, "Erorr getting patient histoery", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}

//    void recent_patients() {
//        ref.document(auth.getUid()).collection("symptoms").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()) {
//                    QuerySnapshot queryDocumentSnapshots=task.getResult();
//                    for(int i=0;i<queryDocumentSnapshots.getDocuments().size();i++){
//                        Log.e("data", queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
//                        symp.add(queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
//                    }
//                    listView.setAdapter(a);
//
//                }
//            }
//        });
//
//    }


//
//    void addr_latln() {
//        Geocoder geocoder = new Geocoder(doctor.this, Locale.ENGLISH);
//        try {
//            List<Address> addresses = geocoder.getFromLocationName("daisy chs sector 6 evershine city vasai mumbai", 3);
////Log.e("latlalaltatlatl",addresses);
//            if (addresses.size() > 0) {
//                Address fetchedAddress = addresses.get(0);
//                double lat = fetchedAddress.getLatitude();
//                double lon = fetchedAddress.getLongitude();
//                StringBuilder strAddress = new StringBuilder();
//                for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
//                    strAddress.append(fetchedAddress.getAddressLine(i));
//                }
//                String latlng = "lat=" + lat + "   long=" + lon;
//                Log.e("adddressssssss", latlng);
//                t.setText(latlng);
//                Log.e("adddressssssss", strAddress.toString());
//
//            } else {
//                t.setText("SEARCHING FOR ADdRESS");
//                Log.e("errrrorororooror", "Searching Current Address");
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            t.setText("could not get adress");
//            Log.e("catchhhhh", "Could not get address..!");
//        }
//
//    }
//
//    void lat_addr(String latitude, String longitude) {
//        Log.e("in", "lat_addr");
//        Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
//        try {
//            List<Address> addresses = geocoder.getFromLocation(Float.parseFloat(latitude), Float.parseFloat(longitude), 1);
//            Log.e("addresss", "" + addresses);
////        List a= (List) addresses.get(0);
//            Log.e("adddressssssss_1", "" + addresses.get(0).getAddressLine(0));
//            final_address = addresses.get(0).getAddressLine(0);
//            display_location.setText("Your Location : " + final_address);
//            data.put("location", final_address);
//            ref.document(auth.getUid()).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    Log.e("Address set", "scucssdfsd");
//                }
//            });
//
////        if (addresses.size() > 0) {
//////            Address fetchedAddress = addresses.get(0);
//////            StringBuilder strAddress = new StringBuilder();
//////            for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
//////                strAddress.append(fetchedAddress.getAddressLine(i)).append(" ");
//////            }
////
////
////        } else {
////            Log.e("errrrorororooror","Searching Current Address");
////        }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.e("catchhhhh", "Could not get address..!");
//        }
//    }
