package com.example.pyproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.internal.ObjectConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class homepage extends AppCompatActivity {
    TextView name, age, gender, disease,nex;
    Button clear;
//    FloatingActionButton fabb;
//    String p_name, p_age, p_gender, p_phone, uid, doc_pati_chanel, p_location;
//    String predi_disease="";
    String uid;
    ListView listView;
    EditText symptoms;
    Button add_symp, predict_button;
    FirebaseFirestore firebaseFirestore;
    CollectionReference ref;
    FirebaseAuth auth;
    ArrayAdapter a;
    String finaldate,finaltime;
    String phone="";
    //    String path = "";
    Map<String, Object> data = new HashMap<>(); Map<String, Object> dataaa = new HashMap<>();
//    Map<String, Object> doc_pati_data = new HashMap<>();
    ArrayList<String> symp = new ArrayList<String>();
    Bundle b;
    DatabaseReference database_1;
    String m_Text="";
//    DatabaseReference myRef_1 ;
private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();
        uid=auth.getUid();
//        fabb=findViewById(R.id.mapbutt);
        nex=findViewById(R.id.nex);
        add_symp = findViewById(R.id.upload_data);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
//        phone = findViewById(R.id.phone);
        symptoms = findViewById(R.id.symptoms);
        predict_button = findViewById(R.id.predict_butt);
        disease = findViewById(R.id.disease);
        b = getIntent().getExtras();
        firebaseFirestore = FirebaseFirestore.getInstance();
        ref = firebaseFirestore.collection("data");
        Log.e("dateeeeee",""+finaldate+finaltime);
//clear=findViewById(R.id.clear);

//fabb.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        Intent i=new Intent(homepage.this,MapsActivity.class);
//        startActivity(i);
//    }
//});


        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }


nex.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        nextt(disease.getText().toString());
    }
});

        try {
            Log.e(" ADDING DATA", "DATA ");

            database_1 = FirebaseDatabase.getInstance("https://pyproj-8523a-default-rtdb.firebaseio.com/").getReference();
            database_1.child("Prediction").setValue("").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.e("data","Added : \"\"");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("data","error adding data in realtime database : "+e);
                }
            });

//            myRef_1 = database.;
        } catch (Exception e) {
            Log.e("ERROR ADDING DATA", "DATA " + e);

        }

//
        try {
get_patient_data(auth.getUid());
new_session();
        } catch (Exception e) {
            Log.e("EXCEPTION ERRPE", "" + e);
        }

//disease.setText("Disease to be Predicted");
        listView = findViewById(R.id.past_symptoms_list);
        a = new ArrayAdapter(this, R.layout.sympto_item, symp);
//        listView.setAdapter(a);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("item", symp.get(position));

                remove_symp(position);
                String it=symp.get(position);
                Log.e(" datadaa","da : " +it);



                return true;
            }
        });

//        try {
//            get_symp();
//        } catch (Exception e) {
//            Log.e("ERROR e", "" + e);
//        }

        add_symp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String sympto = symptoms.getText().toString();
                Log.e("ENTERED SYMP", "dataa :" + sympto);
                if (sympto.equals("") || sympto == null) {
                    Log.e("null", "data is nulll");
                }
                else {
                    //TODO:here
                    LocalDate d= LocalDate.now();
                    LocalTime t=LocalTime.now();
                    String year=String.valueOf(d.getYear());
                    String mon=String.valueOf(d.getMonthValue());
                    String day=String.valueOf(d.getDayOfMonth());
                    String hou=String.valueOf(t.getHour());
                    String min=String.valueOf(t.getMinute());
                    String sec=String.valueOf(t.getSecond());
                    finaldate=year+mon+day;
                    finaltime=hou+min+sec;
                    Date ds=new Date();
//                    tt(sympto);
                    Log.e("data is not null", "data is : " +finaldate+finaltime);
//                    Log.e("data is", "data is : " +ds.getTime());

                    data.put("symptoms", symptoms.getText().toString());
                    data.put("time",hou+":"+min+":"+sec+"  "+day+":"+mon+":"+year);
                    ref.document(uid).collection("symptoms").document().set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            ref.document(uid).collection("symptoms").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        QuerySnapshot queryDocumentSnapshots = task.getResult();
                                        Log.e("symptomps", "" + queryDocumentSnapshots.getDocuments().size());
                                        symp.clear();
                                        for (int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++) {
                                            if (queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString() == "" || queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString() == null) {
                                                Log.e("null", "null");
                                                queryDocumentSnapshots.getDocuments().remove(i);
                                            } else {
                                                Log.e("data", queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
                                                symp.add(queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
                                            }
                                        }
                                        Log.e("sympppppp", "" + symp.size());
                                        Log.e("sympppppp", "" + symp);
                                        listView.setAdapter(a);
                                        symptoms.getText().clear();

                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("error", "gettting symptoms");
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("ERROR adding", "Syotisjhdn");
                            Toast.makeText(homepage.this, "error adding symptomps", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });
//        try {
//            get_predicted_desease();
//        } catch (Exception e) {
//            Log.e("", "" + e);
//        }
        predict_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                path=symp.toString();
//                Log.e("path", "in uploading : " + path);
                database_1.child("symptoms").removeValue();
                Log.e("settingn", "predicted data"+symp);
                database_1.child("Prediction").setValue("Predicting.....").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.e("data","Added : \"\"");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("data","error adding data in realtime database : "+e);
                    }
                });

                setting_predict_data();
                try {
                    get_predicted_desease();
                } catch (Exception e) {
                    Log.e("", "" + e);
                }

                Toast.makeText(homepage.this, "predict test", Toast.LENGTH_LONG).show();
                Log.e("this", "predict test");
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i =new Intent(homepage.this,disea_history.class);
        startActivity(i);
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
                        ref.document(uid).update("alt number",m_Text);
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
                Intent i =new Intent(homepage.this,MainActivity.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    void send_mess(String num,String sy){

        String sms = name.getText().toString()+" Has Been Diagnosed With "+sy;
        String phoneNum = num;
        if(!TextUtils.isEmpty(sms) && !TextUtils.isEmpty(phoneNum)) {
            if(checkPermission()) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNum, null, sms, null, null);
            }else {
                Toast.makeText(homepage.this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(homepage.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);

    }



void tt(String sympto){
    Log.e("data is not null", "data is : " + sympto);

    data.put("symptoms", symptoms.getText().toString());
    ref.document(uid).collection(finaldate).document(finaltime).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {

            ref.document(uid).collection("symptoms").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        QuerySnapshot queryDocumentSnapshots = task.getResult();
                        Log.e("symptomps", "" + queryDocumentSnapshots.getDocuments().size());
                        symp.clear();
                        for (int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++) {
                            if (queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString() == "" || queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString() == null) {
                                Log.e("null", "null");
                                queryDocumentSnapshots.getDocuments().remove(i);
                            } else {
                                Log.e("data", queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
                                symp.add(queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
                            }
                        }
                        Log.e("sympppppp", "" + symp.size());
                        Log.e("sympppppp", "" + symp);
                        listView.setAdapter(a);

                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("error", "gettting symptoms");
                }
            });
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Log.e("ERROR adding", "Syotisjhdn");
            Toast.makeText(homepage.this, "error adding symptomps", Toast.LENGTH_SHORT).show();
        }
    });
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
phone=document.get("alt number").toString();

                        String p_age = document.get("age").toString();
                        String p_phone = document.get("phone").toString();
//                        Log.e("data",p_age+""+p_phone);
                        String p_gender = document.get("gender").toString();
                        String p_uid = document.get("uid").toString();
//                        String p_loc = document.get("location").toString();
                        Log.e("data", p_uid + "" + p_name + "" + p_age + "" + p_gender  + "" + p_phone);
//
//                        doc_pati_data.put("name", p_name);
//                        doc_pati_data.put("uid", uid);
//                        doc_pati_data.put("age", p_age);
//                        doc_pati_data.put("gender", p_gender);
                        name.setText(p_name);

                        age.setText(p_age);
                        gender.setText(p_gender);
//                        phone.setText(p_phone);
//                        Intent i = new Intent(doctor.this, patient_details_to_doc.class);
//                        i.putExtra("uid", p_uid);
//                        i.putExtra("name", p_name);
//                        i.putExtra("age", p_age);
//                        i.putExtra("phone", p_phone);
//                        i.putExtra("gender", p_gender);
//                        i.putExtra("doc_patient_channel", auth.getUid() + "-" + p_uid);
//                        i.putExtra("patient_location", p_loc);
//                        startActivity(i);


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

    void setting_predict_data() {
        for (int i = 0; i < symp.size(); i++) {
            Log.e("ITEM", "" + symp.get(i));
            final int finalI = i;
//            database_1.child("symptoms").setValue("");
            database_1.child("symptoms").child(String.valueOf(i)).setValue(symp.get(i)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.e("data","Added : "+symp.get(finalI));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("data","error adding data in realtime database : "+e);
                }
            });


        }



    }
    void set_new(String predi_disea){
        LocalDate d= LocalDate.now();
        LocalTime t=LocalTime.now();
        String year=String.valueOf(d.getYear());
        String mon=String.valueOf(d.getMonthValue());
        String day=String.valueOf(d.getDayOfMonth());
        String hou=String.valueOf(t.getHour());
        String min=String.valueOf(t.getMinute());
        String sec=String.valueOf(t.getSecond());
        String daaaa=year+mon+day;
        String tttttt=hou+min+sec;
String ss=String.join(",",symp);
        dataaa.put("symptoms", ss);
        dataaa.put("disease", predi_disea);
        dataaa.put("time",hou+":"+min+":"+sec);
        dataaa.put("date",day+"-"+mon+"-"+year);
        ref.document(auth.getUid()).collection("disease").document().set(dataaa).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("SET","new data");
            }
        });

    }

//  void set_new(String predi_disea){
//        LocalDate d= LocalDate.now();
//        LocalTime t=LocalTime.now();
//        String year=String.valueOf(d.getYear());
//        String mon=String.valueOf(d.getMonthValue());
//        String day=String.valueOf(d.getDayOfMonth());
//        String hou=String.valueOf(t.getHour());
//        String min=String.valueOf(t.getMinute());
//        String sec=String.valueOf(t.getSecond());
//        String daaaa=year+mon+day;
//        String tttttt=hou+min+sec;
//String ss=String.join(",",symp);
//
//
//ref.document(auth.getUid()).collection("disease").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//    @Override
//    public void onComplete(@NonNull  Task<QuerySnapshot> task) {
//        if(task.isSuccessful()) {
//            QuerySnapshot aa=task.getResult();
//            for(int i=0;i<aa.getDocuments().size();i++){
//                Log.e("","");
//
//            }
//
//
//        }
//    }
//});
//
//
//
//
//
//        dataaa.put("symptoms", ss);
//        dataaa.put("disease", predi_disea);
//        dataaa.put("time",hou+":"+min+":"+sec);
//        dataaa.put("date",day+"-"+mon+"-"+year);
//        ref.document(auth.getUid()).collection("disease").document().set(dataaa).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Log.e("SET","new data");
//            }
//        });
//
//    }

    void get_predicted_desease() {
        database_1.child("Prediction").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    String value = dataSnapshot.getValue().toString();
//                    Intent i=new Intent(patient_details_to_doc.this,precaution.class);
//                    i.putExtra("disease",value);
//                    startActivity(i);
//                    predi_disease=value;
                    if(value.equals("Predicting.....") || value.equals("")){
                        Log.e("NULL", "skipping et");
                    }
                    else {
                        set_new(value);
                        send_mess(phone,value);
                    }
                    disease.setText(value);

//                  nextt(value);
                    Log.e("TAG", "Value is: " + value);
                } catch (Exception e) {
                    Log.e("error", "" + e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TAG", "Failed to read value.", databaseError.toException());
            }
        });
//        database_1.child("Prediction").
    }

void nextt(String value){
    Intent i = new Intent(homepage.this,webv.class);
    i.putExtra("disease",value);
    startActivity(i);
}
    void remove_symp(int pos) {

        Log.e("removing symp", "helll"+pos);
        ref.document(uid).collection("symptoms").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.e("TAG", document.getId() + " => " + document.getData().get("symptoms"));
                        if(document.getData().get("symptoms").toString().equals(symp.get(pos))){
Log.e("removing",""+document.getData().get("symptoms").toString() );

                            ref.document(uid).collection("symptoms").document(document.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.e("REMOVEDDD","SUCESS");
                                    symp.remove(pos);
                            a.notifyDataSetChanged();
                                }
                            });


//

                        }
                    }
                } else {
                    Log.w("TAG", "Error getting documents.", task.getException());
                }

            }
        });


//        ref.document(uid).collection("symptoms").document().set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                ref.document(uid).collection("symptoms").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            QuerySnapshot queryDocumentSnapshots = task.getResult();
//                            Log.e("symptomps", "" + queryDocumentSnapshots.getDocuments().size());
////                            queryDocumentSnapshots.getDocuments().remove(pos);
//
//                            Log.e("sympppppp", "" + symp.size());
//                            Log.e("sympppppp", "" + symp);
////                            listView.setAdapter(a);
//
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e("error", "gettting symptoms");
//                    }
//                });
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.e("ERROR adding", "Syotisjhdn");
//                Toast.makeText(homepage.this, "error adding symptomps", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
    void  new_session() {

        Log.e("new session", "helll");
        ref.document(uid).collection("symptoms").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                   int siz= task.getResult().size();
                    Log.e("size",""+siz);
                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        ccc++;
                        Log.e("TAG", document.getId() + " => " + document.getData().get("symptoms"));
//     for(int i=0;i<siz;i++){

//                        if (document.getData().get("symptoms").toString().equals(symp.get(i))) {
                            Log.e("removing", "" + document.getData().get("symptoms").toString());

                            ref.document(uid).collection("symptoms").document(document.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.e("REMOVEDDD", "SUCESS");
//                                    symp.remove(finalIi);
                                    a.notifyDataSetChanged();
                                }
                            });


//

//                    }
//                }
                    }
                } else {
                    Log.w("TAG", "Error getting documents.", task.getException());
                }


            }
        });


//        ref.document(uid).collection("symptoms").document().set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                ref.document(uid).collection("symptoms").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            QuerySnapshot queryDocumentSnapshots = task.getResult();
//                            Log.e("symptomps", "" + queryDocumentSnapshots.getDocuments().size());
////                            queryDocumentSnapshots.getDocuments().remove(pos);
//
//                            Log.e("sympppppp", "" + symp.size());
//                            Log.e("sympppppp", "" + symp);
////                            listView.setAdapter(a);
//
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e("error", "gettting symptoms");
//                    }
//                });
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.e("ERROR adding", "Syotisjhdn");
//                Toast.makeText(homepage.this, "error adding symptomps", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
//
//    void get_symp() {
//        Log.e("in get symp", "helll");
//        Log.e("in get symp", "helll");
//        Log.e("in get symp", "helll");
//        Log.e("in get symp", "helll");
//        ref.document(uid).collection("symptoms").document().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                ref.document(uid).collection("symptoms").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            QuerySnapshot queryDocumentSnapshots = task.getResult();
//                            Log.e("symptomps", "" + queryDocumentSnapshots.getDocuments().size());
//                            symp.clear();
//                            for (int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++) {
//                                try {
//                                    if (queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString().equals("") || queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString() == null) {
//                                        Log.e("null", "null");
////                                        queryDocumentSnapshots.getDocuments().remove(i);
//                                        queryDocumentSnapshots.getDocuments().remove(i);
//                                    } else {
////                                        if(queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString() == queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString()){
////                                            Log.e("null", "removing data");
////                                            queryDocumentSnapshots.getDocuments().remove(i);
////
////                                        }
////                                        else{
//                                        Log.e("data", queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
//                                        symp.add(queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
////                                        }
//
//                                    }
////                                Log.e("symptomps", "" + queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
//                                } catch (Exception e) {
//                                    Log.e("ERROR", "in getttingggggg");
//                                }
//
//
//                            }
//                            Log.e("sympppppp", "" + symp.size());
//                            Log.e("sympppppp", "" + symp);
//                            listView.setAdapter(a);
////                            set_or_get_patiendocdetails();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e("error", "gettting symptoms");
//                    }
//                });
//            }
//        });
//
//
//    }


//    void set_or_get_patiendocdetails() {
//        Log.e("in", "getting details");
//        ref.document(auth.getUid())
//                .collection("patient history")
//                .document(doc_pati_chanel).set(doc_pati_data).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Log.e("patien details saved", "in doc");
//            }
//        });
//
//        for (int i = 0; i < symp.size(); i++) {
//            Map<String, Object> doc_pati_symp = new HashMap<>();
//            doc_pati_symp.put("symptoms", symp.get(i));
//
//            ref.document(auth.getUid())
//                    .collection("patient history")
//                    .document(doc_pati_chanel).collection("patient symptoms").document()
//                    .set(doc_pati_symp).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    Log.e("patien sympom saved", "in doc");
//                }
//            });
//        }
//
//    }

}  //
//    void get(){
//        database_1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue().toString();
//                Log.e("TAG", "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.e("TAG", "Failed to read value.", error.toException());
//            }
//        });
//    }



//TODO: updating removed data from listview in firebase
//    void get_symp() {
//
//        ref.document(uid).collection("symptom").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    Log.e("TASKKKKK","SUCESSFULLLLL");
//                    QuerySnapshot queryDocumentSnapshots = task.getResult();
//                    Log.e("snap", "" + queryDocumentSnapshots.getDocuments().size());
//                    for (int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++) {
//                        if (queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString() == "" || queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString() == null) {
//                            Log.e("null", "null");
//                        } else {
//                            Log.e("data_in_on create", queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
//                            symp.add(queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
//                        }
//                    }
//                    Log.e("sympppppp", "" + symp.size());
//                    Log.e("sympppppp", "" + symp);
//                    Log.e("path", "in getSymp");
////                    path = symp.toString();
////                    Log.e("path", "null" + path);
//                    listView.setAdapter(a);
//
//                    set_or_get_patiendocdetails();
//
//                }
//            }
//        });
//
//    }