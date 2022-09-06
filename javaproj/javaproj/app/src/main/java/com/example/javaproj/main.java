package com.example.javaproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.collection.LLRBNode;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main extends AppCompatActivity {
    public static View.OnClickListener myOnClickListener;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<dataa> data;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref=db.collection("bdata");
    String seats="";
//    list destination={"Mumbai to Bangalore","Mumbai to Hyderabad","Mumbai to Delhi"};
//    list fare={"1000","1143","1050","1000","900","1052","808","1243","1052","2500","2500","2600","2600","2512"};
//    list bus={"Ocean Bus A P Travels","Sharma Transports","National travels ntsb",
//            "VRL Travels","SRS Travels","IntrCity SmartBus","Jabbar Travels",
//            "LIMOLINER","IntrCity SmartBus","Indian express","Hans Travels","Hans Travels","Hans Travels","Apple travels"};
//    list time={"09:15","18:15","18:00","21:00","18:00","16:00","16:15","18:20","18:20","17:45","14:45","19:06","16:45","19:06","20:00"}
String[] date={"27 Mar 2021","30 Mar 2021","08 Apr 2021","21 Apr 2021","15 May 2021","17 May 2021","25 May 2021","04 Dec 2021","21 Apr 2021","15 May 2021","17 May 2021","25 May 2021","04 Dec 2021"};
    ImageButton b;
    EditText to ;
String[] from={"Mumbai","Mumbai","Mumbai","Mumbai","Mumbai","Mumbai","Mumbai","Mumbai","Mumbai","Mumbai","Mumbai","Mumbai","Mumbai"};
    String[] destination={"Mumbai","Goa","Chennai","Bangloure","Kolkata","Goa","Chennai","Bangloure","Kolkata","Goa","Chennai","Bangloure","Kolkata"};
    String[] fare={"2600","1400","1334","900","1050","4300","2660","2299","1762","46465","1400","1334","900"};
    String[] bus={"Konduskar Travels Pvt. Ltd","Orange Tours and Travels Platinum","Hans Travels","Canara Pinto",
            "National travels","JGD Travels Pvt Ltd.","Sri Krishna Rath","Sri Sai Atmaram Manish Travels","Orange Tours and Travels Platinum","Hans Travels","Canara Pinto","Sri Krishna Rath","Neeta tours and travels","....."};
    String[] time={"16:00","16:00","16:45","15:30","12:25","23:50","15:30","19:05","14:15","45:64","19:05","14:15","45:64"};
String[] avai={"40","12","30","44","20","44","32","50","40","33","44","22","29"};

String doc="";

    List finaldest = new ArrayList();
    List finalname = new ArrayList();
    List finalfare = new ArrayList();
    List finaltime = new ArrayList();
    List finalfrom = new ArrayList();
    List finaldate = new ArrayList();
    List finalseat = new ArrayList();
    List cola = new ArrayList();
    List finaldoc = new ArrayList();

    FirebaseFirestore auth=FirebaseFirestore.getInstance();
//    List sea=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        to =findViewById(R.id.to);
        b=findViewById(R.id.search);
        b.setBackgroundColor(Color.TRANSPARENT);
        to.setTextColor(Color.WHITE);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        myOnClickListener = new MyOnClickListener(this);
//add();

ImageButton sout=findViewById(R.id.signout);
sout.setBackgroundColor(Color.TRANSPARENT);
sout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Log.e("Signout","out");
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(main.this,MainActivity.class);
        startActivity(i);
    }
});

//        removedItems = new ArrayList<Integer>();


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finaldest.clear(); finalfare.clear();
                finalfrom.clear();finaltime.clear();
                finalname.clear();finaldate.clear();finalseat.clear();
                cola.clear();finaldoc.clear();


                String ttt=to.getText().toString();
                Log.e("text",""+ttt);
                getdat(ttt,v);


//                for(int i=0 ;i<destination.length;i++){
//                    Log.e("text",""+ttt);
//                    Log.e("it",""+destination[i]);
//                    if(ttt.equals(destination[i])){
//                        Log.e("found",""+destination[i]);
////                       finaldest.add(destination[i]);
////                        finalname.add(bus[i]);
////                        finalfare.add(fare[i]);
////                        finaltime.add(time[i]);
////                        finalfrom.add(from[i]);
//                    }
//                }
//

            }
        });




    }

    private class MyOnClickListener implements View.OnClickListener {
        private  Context context;
        public MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
//            if(){}





            Intent i= new Intent(main.this,detail.class);
            int po=recyclerView.getChildPosition(v);
            if(cola.get(po) == "red"){
                Snackbar.make(v, "No Seats Available", Snackbar.LENGTH_SHORT).setBackgroundTint(Color.RED).show();
            }
            else {


                i.putExtra("name", finalname.get(po).toString());
                i.putExtra("to", finaldest.get(po).toString());
                i.putExtra("fare", finalfare.get(po).toString());
                i.putExtra("time", finaltime.get(po).toString());
                i.putExtra("from", finalfrom.get(po).toString());
                i.putExtra("date", finaldate.get(po).toString());
                i.putExtra("seats", finalseat.get(po).toString());
                Log.e("doc","doc"+finaldoc.get(po).toString());
                i.putExtra("doc", finaldoc.get(po).toString());

//Log.e("POSITION", "pos : "+recyclerView.getChildPosition(v));
                startActivity(i);

            }
        }
    }


void getdat(String des,View v){
        Log.e("gettinf","data");
//    List finaldest = new ArrayList();
//    List finalname = new ArrayList();
//    List finalfare = new ArrayList();
//    List finaltime = new ArrayList();
//    List finalfrom = new ArrayList();
        ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override

            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                Log.e("gettinf","dataaaaaaaa");
                if (task.isSuccessful()) {
                    Log.e("dessss",""+ des);
                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.e("dataaa", document.getId() + " => " + document.getData().get("name"));
                        String d=document.getData().get("to").toString();
                        Log.e("store",""+ d);
                        if(des.equals(d)  ) {
                            Log.e("in iffffffffffff","data");
                            Log.e("found",""+des);

                            finaldoc.add(document.getId());
                            finaldest.add(document.getData().get("to"));
                            finalname.add(document.getData().get("name"));
                            finalfare.add(document.getData().get("fare"));
                            finaltime.add(document.getData().get("time"));
                            finalfrom.add(document.getData().get("from"));
                            finaldate.add(document.getData().get("date"));
                            finalseat.add(document.getData().get("avai"));
//                            seats=document.getData().get("avai").toString();
//                            document.getData().get("name");
//                            document.getData().get("from");
//                            document.getData().get("to");
//                            document.getData().get("fare");
//                            document.getData().get("time");


                        }
                        else{
                            Snackbar.make(v, "Non Found ", Snackbar.LENGTH_SHORT).setBackgroundTint(Color.RED).show();
                        }
                    }
                } else {
                    Log.e("dataaa", "Error getting documents.", task.getException());
                }
                Snackbar.make(v, "Found "+finaldest.size() + " Results", Snackbar.LENGTH_SHORT).setBackgroundTint(Color.RED).show();
                Log.e("text",""+finaldest.size());
                data = new ArrayList<dataa>();
                for (int i = 0; i < finaldest.size(); i++) {
                    Log.e("adding","addiing");

                    Log.e("seat","checling:" + finalseat.get(i));
                    Log.e("seat","sada"+finalseat);

                    if(finalseat.get(i).toString().equals("0")  ){
                        Log.e("null","seat null");

                        cola.add(i,"red");

//                        recyclerView.getChildAt(i).setBackgroundColor(Color.RED);

                    }
                    else{
                        cola.add("some");
                    }



                    data.add(
                            new dataa( finalname.get(i).toString(),finaldest.get(i).toString(),finalfrom.get(i).toString(), finaltime.get(i).toString(), finalfare.get(i).toString() ,cola.get(i).toString())
                    );
//Log.e("seat","s :"+ finalseat);

                }

                adapter = new adapter(data);

//                recyclerView.getChildAt(0).setBackgroundColor(Color.RED);
                recyclerView.setAdapter(adapter);
                Log.e("col",""+cola);
//                for(int i=0;i<cola.size();i++){}


            }
        });
//adapter.notifyDataSetChanged();
}













    void add(){
        for(int i=0 ; i<destination.length;i++) {
            Map<String, Object> user = new HashMap<>();
            user.put("name",bus[i] );
            user.put("from", from[i]);
            user.put("to", destination[i]);
            user.put("time",time[i]);
            user.put("fare",fare[i]);
            user.put("avai",avai[i]);
            user.put("date",date[i]);
            db.collection("bdata")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.e("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("TAG", "Error adding document", e);
                        }
                    });
        }

    }
}