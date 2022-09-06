package com.example.hadop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hadop.costom.CustomListpatient;
import com.example.hadop.costom.Custom_list_data_pati;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.Frame;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class patient extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    SwipeRefreshLayout reffff;
    NavigationView nav;
    FloatingActionButton fab;
    ImageView imageView;
    Dialog dialog;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference ref = firebaseFirestore.collection("data");
    String patient_name;
//    TextView t;
    ListView symp_list;
//    ArrayAdapter a;
    View parentLayout;
    FrameLayout f;
    Button add_location;
    TextView display_location;
    Bundle b;
    String lat,lon;
    String final_address,m_Text;
    CustomListpatient customListpatient;
    Map<String, Object> data = new HashMap<>();
    ArrayList<String> symp = new ArrayList<String>();
    ArrayList<Custom_list_data_pati> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
//        imageView=findViewById(R.id.image);
reffff=findViewById(R.id.refres);
        parentLayout = findViewById(android.R.id.content);
//        f=findViewById(R.id.frag_map);
        add_location=findViewById(R.id.add_loca);
        display_location=findViewById(R.id.your_loca);
        dialog = new Dialog(this);
        fab = findViewById(R.id.fab_patient);
//        t = findViewById(R.id.pati_text);
        Toolbar toolbar = findViewById(R.id.toolbar);
symp_list=findViewById(R.id.symptoms_list);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer);
        nav = findViewById(R.id.navigation_view);
        nav.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        customListpatient = new CustomListpatient(patient.this, arrayList);
        symp_list.setAdapter(customListpatient);


//        a = new ArrayAdapter(this, R.layout.sympto_item, symp);
        display_location.setText("Your Location : Not yet Selected");
try {
    b=getIntent().getExtras();
    lat=b.getString("lat");
    lon=b.getString("lon");
    if(lat == null && lon==null) {
        display_location.setText("Your Location : Not yet Selected");
    }
    else{
        Log.e("got","data00");
        lat_addr(lat,lon);

    }

}
catch (Exception e){
    Log.e("nulll",""+e);
}

reffff.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        past_dataaa();
        reffff.setRefreshing(false);
    }
});

past_dataaa();
get_data();
get_symp();
        add_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                from_add();
                Intent i=new Intent(patient.this,MapsActivity.class);
                startActivity(i);
            }
        });


//        getSupportFragmentManager().beginTransaction().replace(R.id.frag_map, new MapsActivity()).commit();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("OnClick", "In");

               generate(auth.getUid());
            }
        });

symp_list.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
        Intent i =new Intent(patient.this,disease_details.class);
        startActivity(i);



        return true;
    }
});
        symp_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("item",""+arrayList.get(position).getSymptoms());
                Log.e("item",""+arrayList.get(position).getName());
                Log.e("item",""+arrayList.get(position).getDate());
                Intent i=new Intent(patient.this,disease_details.class);
                i.putExtra("symptomps",arrayList.get(position).getSymptoms());
                i.putExtra("disease",arrayList.get(position).getName());
                i.putExtra("date",arrayList.get(position).getDate());
                i.putExtra("time",arrayList.get(position).getTime());
                Log.e("time",arrayList.get(position).getTime());
                startActivity(i);
//                getdat(position);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.cont:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Add Or Change Contact Info.");

                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_PHONE );
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        ref.document(auth.getUid()).update("alt number",m_Text);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                Log.e("textttt is",""+m_Text);
                return true;
            case R.id.out:
                FirebaseAuth.getInstance().signOut();
                Intent i =new Intent(patient.this,MainActivity.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    //void addr_latln(){
//    Geocoder geocoder = new Geocoder(patient.this, Locale.ENGLISH);
//    try {
//        List<Address> addresses = geocoder.getFromLocationName("mumbai", 3);
////Log.e("latlalaltatlatl",addresses);
//        if (addresses.size() > 0) {
//            Address fetchedAddress = addresses.get(0);
//            double lat =  fetchedAddress.getLatitude();
//            double lon = fetchedAddress.getLongitude();
//            StringBuilder strAddress = new StringBuilder();
//            for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
//                strAddress.append(fetchedAddress.getAddressLine(i));
//            }
//            String latlng="lat="+lat+"   long="+lon;
//            Log.e("adddressssssss",latlng);
//            t.setText(latlng);
//            Log.e("adddressssssss",strAddress.toString());
//
//        } else {
//            t.setText("SEARCHING FOR ADdRESS");
//            Log.e("errrrorororooror","Searching Current Address");
//        }
//
//    } catch (IOException e) {
//        e.printStackTrace();
//        t.setText("could not get adress");
//        Log.e("catchhhhh","Could not get address..!");
//    }
//
//}
void lat_addr(String latitude,String longitude){
        Log.e("in","lat_addr");
    Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
    try {
        List<Address> addresses = geocoder.getFromLocation(Float.parseFloat(latitude), Float.parseFloat(longitude), 1);
        Log.e("addresss",""+addresses);
//        List a= (List) addresses.get(0);
        Log.e("adddressssssss_1",""+ addresses.get(0).getAddressLine(0));
final_address=addresses.get(0).getAddressLine(0);
        display_location.setText("Your Location : " + final_address );
data.put("location",final_address);
//        ref.document(auth.getUid()).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Log.e("Address set","scucssdfsd");
//            }
//        });
        ref.document(auth.getUid()).update(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.e("Address set","scucssdfsd");
            }
        });

//        if (addresses.size() > 0) {
////            Address fetchedAddress = addresses.get(0);
////            StringBuilder strAddress = new StringBuilder();
////            for (int i = 0; i < fetchedAddress.getMaxAddressLineIndex(); i++) {
////                strAddress.append(fetchedAddress.getAddressLine(i)).append(" ");
////            }
//
//
//        } else {
//            Log.e("errrrorororooror","Searching Current Address");
//        }

    } catch (IOException e) {
        e.printStackTrace();
        Log.e("catchhhhh","Could not get address..!");
    }
}
    void get_symp() {
        Log.e("in get symp", "helll");
        Log.e("in get symp", "helll");
        Log.e("in get symp", "helll");
        Log.e("in get symp", "helll");
        ref.document(auth.getUid()).collection("symptoms").document().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                ref.document(auth.getUid()).collection("symptoms").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot queryDocumentSnapshots = task.getResult();
                            Log.e("symptomps", "" + queryDocumentSnapshots.getDocuments().size());
                            symp.clear();
                            for (int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++) {
                                try {
                                    if (queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString().equals("") || queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString() == null) {
                                        Log.e("null", "null");
//                                        queryDocumentSnapshots.getDocuments().remove(i);
                                        queryDocumentSnapshots.getDocuments().remove(i);
                                    } else {
//                                        if(queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString() == queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString()){
//                                            Log.e("null", "removing data");
//                                            queryDocumentSnapshots.getDocuments().remove(i);
//
//                                        }
//                                        else{
                                        Log.e("data", queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
                                        symp.add(queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
//                                        }

                                    }
//                                Log.e("symptomps", "" + queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
                                } catch (Exception e) {
                                    Log.e("ERROR", "in getttingggggg");
                                }


                            }
                            Log.e("sympppppp", "" + symp.size());
                            Log.e("sympppppp", "" + symp);
//                            symp_list.setAdapter(a);
//                            set_or_get_patiendocdetails();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("error", "gettting symptoms");
                    }
                });
            }
        });


    }

void get_data(){
    ref.document(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.e("TAG", "DocumentSnapshot data: " + document.getString("name"));
                    patient_name = document.getString("name");
//                    t.setText("Patient Name: " + patient_name);
                    getSupportActionBar().setTitle("Patient Name: " + patient_name);
                    Snackbar.make(parentLayout, "Welcome Patient " + patient_name, Snackbar.LENGTH_SHORT).setBackgroundTint(Color.YELLOW).setTextColor(Color.BLACK).show();
                } else {
                    Log.e("TAG", "No such document");
                }

            }
        }
    });
}

    void popupw(Bitmap bitmap) {
        Log.e("pop up", "In");
//        imageView=dialog.findViewById(R.id.image_v);
        dialog.setContentView(R.layout.qr_display);
        imageView = dialog.findViewById(R.id.img);
        imageView.setImageBitmap(bitmap);

        TextView x = dialog.findViewById(R.id.x);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();


    }

    void generate(String text) {
        Log.e("generate", "In");
//        String text = "praveeen";// Whatever you need to encode in the QR code
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            popupw(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        Log.e("generate", "done");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.e("itemmmm", "" + item.getTitle());
        Log.e("itemmmm", "" + item.getItemId());

//        Log.e("trying", "to change");
////        if (item.getTitle() == "somwthing 1") {
////            getSupportFragmentManager().beginTransaction().replace(R.id.frag, new frag_test1()).commit();
////        } else if (item.getTitle() == "somwthing 2") {
////            getSupportFragmentManager().beginTransaction().replace(R.id.frag, new frag_test()).commit();
////        }
//        Log.e("done", "to change");

        drawer.closeDrawer(GravityCompat.START);
        switch (item.getTitle().toString()) {
            case "Logout":  signout();
                break;
            case "someasd": break;

            default:
                Log.e("switch", "" + item.getTitle());
                break;
        }

        return true;

    }
    void signout(){ Log.e("in signout"," signout");

        try{
            Toast.makeText(this, "Signout Successful", Toast.LENGTH_SHORT).show();
            Log.e("in signout","starting signout");
            FirebaseAuth.getInstance().signOut();
            Log.e("in signout","done signout");
            Intent i=new Intent(patient.this,MainActivity.class);
            startActivity(i);
        }
        catch (Exception e){
            Snackbar.make(parentLayout,"ERROR SIGNOUT",Snackbar.LENGTH_SHORT).show();
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(patient.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);

    }

    void past_dataaa() {
        arrayList.clear();
        ref.document(auth.getUid()).collection("disease").orderBy("time", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot queryDocumentSnapshots = task.getResult();
                    Log.e("getting", "recent symptomps" + queryDocumentSnapshots.getDocuments().size());


                    for (int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++) {
                        try {
                            Log.e("data", queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
                            String date = queryDocumentSnapshots.getDocuments().get(i).get("date").toString();
                            String time = queryDocumentSnapshots.getDocuments().get(i).get("time").toString();
                            String symp = queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString();
                            String dis_his = queryDocumentSnapshots.getDocuments().get(i).get("disease").toString();
                            Log.e("data", "adding in list");
                            arrayList.add(new Custom_list_data_pati(String.valueOf(i + 1), dis_his, symp, date, time));
//                        recent_uid.add(p_uid);
//                        symp.add(queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
                        }
                        catch (Exception e){
                            Log.e("Ecvepr",""+e);
                        }
                    }
//                    listView.setAdapter(a);
                    Log.e("data", "adding in list" + arrayList);
                    customListpatient = new CustomListpatient(patient.this, arrayList);
                    symp_list.setAdapter(customListpatient);
                } else {
                    Toast.makeText(patient.this, "Erorr getting patient histoery", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}