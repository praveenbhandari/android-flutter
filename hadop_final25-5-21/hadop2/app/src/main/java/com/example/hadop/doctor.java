package com.example.hadop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class doctor extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView nav;private static final int PERMISSION_REQUEST_CODE = 1;
    FloatingActionButton fab;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference ref = firebaseFirestore.collection("data");
    String scn_data = "";
    String doc_name;
    TextView t, display_location;
    View parentLayout;
    ListView listView;

    CustomList customList;
    ArrayList<Custom_list_data> arrayList = new ArrayList<>();
    ArrayList<String> recent_uid = new ArrayList<>();
    SwipeRefreshLayout rrr;

    //    ArrayList<String> symp=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        fab = findViewById(R.id.fab_doc);
        Toolbar toolbar = findViewById(R.id.toolbar);

            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }

        setSupportActionBar(toolbar);
//        doc_loc = findViewById(R.id.doc_loc);
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
//        arrayList.add(new Custom_list_data_pati("1", " asdad","cough","12222"));
//        arrayList.add(new Custom_list_data_pati( "2"," asdasd","cold","12333"));
//        arrayList.add(new Custom_list_data_pati( "3"," adfg","fever","1300"));
        customList = new CustomList(this, arrayList);
//        listView.setAdapter(customList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              display.snakbar(view, String.valueOf(position+1));
//                Intent i = new Intent(doctor.this,patient_details_to_doc.class);
                try {
                    get_patient_data(recent_uid.get(position));
                }
                catch (Exception e){
                    Log.e("erro",""+e);
                }
//                startActivity(i);
                Toast.makeText(doctor.this, "clicked on " + position, Toast.LENGTH_SHORT).show();
            }
        });
        Log.e("getting", "recent patients");
        try {
            recent_patients();
        }
        catch (Exception e){
            Log.e("erro",""+e);
        }
        Log.e("done getting", "recent patients");


        try {
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
             }
        catch (Exception e){
            Log.e("erro",""+e);
        }



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


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(doctor.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);

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
try {
    get_patient_data(result.getContents());
}
catch (Exception e){
    Log.e("errer",""+e);
}
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
                        String p_phone = document.get("alt number").toString();
                        Log.e("data",""+p_phone);
                        String p_gender = document.get("gender").toString();
                        String p_uid = document.get("uid").toString();
                        String p_loc="";
                        try {
                           p_loc  = document.get("location").toString();
                        }
                        catch(Exception e){
                            Log.e("ERROR","getting loca :"+e);
                        }
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
                    requestPermission();
                    Toast.makeText(doctor.this, "Erorr getting patient histoery", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
