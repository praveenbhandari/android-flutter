package com.example.pyproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.internal.InternalTokenProvider;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class disea_history extends AppCompatActivity {
SwipeRefreshLayout s;
    CustomList customList;
    ArrayList<Custom_list_data> arrayList = new ArrayList<>();
    ListView lvv;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    CollectionReference ref;
    String m_Text="";
    FloatingActionButton b;int ii=0;
    ArrayList<String> dddate=new ArrayList<>() ;
    ArrayList<String> tttime=new ArrayList<>() ;
    ArrayList<String> sssymp=new ArrayList<>() ;
    ArrayList<String> dddisease=new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.onBackPressed();
        setContentView(R.layout.activity_disea_history);
        b=findViewById(R.id.bu);
        lvv=findViewById(R.id.hist_list);
        auth=FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        ref = firebaseFirestore.collection("data");
//        Handler h=new Handler();

//        past_dataaa();
s=findViewById(R.id.refr);
//        h.postDelayed(new Runnable() {
//            @Override
//            public void run() {
                ii++;
past_dataaa();
//Log.e("runnable","in runnable:  "+ii++);
//            }
//        },1000);
//past_dupli();
s.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        Log.e("in past data","pasttttt");
        arrayList.clear();
        past_dataaa();
//        past_dupli();

//        Log.e("dupli list   ",""+dup);
        Log.e("in past data","doneeeee pasttttt");
s.setRefreshing(false);
    }
});

//Log.e("dupli list   ",""+dup);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(disea_history.this, homepage.class);
                startActivity(i);
            }
        });

//        customList = new CustomList(disea_history.this, arrayList);
//        lvv.setAdapter(customList);

//        arrayList.add(new Custom_list_data(String.valueOf(i + 1), name, "some", "date"));
//        customList = new CustomList(history.this, arrayList);
//        l.setAdapter(customList);


        lvv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Log.e("item",""+arrayList.get(position).getSymptoms());
                Log.e("item",""+arrayList.get(position).getName());
                Log.e("item",""+arrayList.get(position).getDate());
                Intent i=new Intent(disea_history.this,disea_details.class);
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
                        ref.document(FirebaseAuth.getInstance().getUid()).update("alt number",m_Text);
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
                Intent i =new Intent(disea_history.this,MainActivity.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void past_dataaa() {
//        dddate.add("");
//        tttime.add("");
//        sssymp.add("");
//        dddisease.add("");
        dddate.clear();tttime.clear();sssymp.clear();dddisease.clear();
//        ArrayList<String> dup=new ArrayList<>();
        ref.document(auth.getUid()).collection("disease").orderBy("time", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
//                    DocumentSnapshot d=task.getResult();
                    QuerySnapshot queryDocumentSnapshots = task.getResult();
                    Log.e("getting", "recent symptomps" + queryDocumentSnapshots.getDocuments().size());
                    QuerySnapshot qq=task.getResult();
//remov_duplica();

                    for (int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++) {
//                        dup.add();
                    Log.e("id",""+queryDocumentSnapshots.getDocuments().get(i).getId());
//                    og.add(queryDocumentSnapshots.getDocuments().get(i).getId());

                        try {
                            Log.e("data", queryDocumentSnapshots.getDocuments().get(i).get("disease").toString());
                            String date = queryDocumentSnapshots.getDocuments().get(i).get("date").toString();
                            String time = queryDocumentSnapshots.getDocuments().get(i).get("time").toString();
                            String symp = queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString();
                            String dis_his = queryDocumentSnapshots.getDocuments().get(i).get("disease").toString();
//                            Log.e("add","...........");
//                            dddate.add(date);
//                            tttime.add(time);
//                            sssymp.add(symp);
//                            dddisease.add(dis_his);
//                            Log.e("add","..........");
                            if(tttime.contains(time) && dddate.contains(date) && sssymp.contains(symp) && dddisease.contains(dis_his)
                            ) {
                               Log.e("skipping",""+date+":"+time+":"+symp+":"+dis_his);
                               ref.document(auth.getUid()).collection("disease").document(queryDocumentSnapshots.getDocuments().get(i).getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull  Task<Void> task) {
                                       Log.e("removing","removed"+date+time+dis_his+symp);
                                   }
                               });
                            }
                            else{
                                Log.e("ssssie",""+dddate.size());
                                dddate.add(date);
                                tttime.add(time);
                                sssymp.add(symp);
                                dddisease.add(dis_his);
                            }
//                            Log.e("data", "adding in list");
//                            for(int j = 0; j < queryDocumentSnapshots.getDocuments().size(); j++) {
//                                String ddate="",ttime="",ssymp="",ddis_his="";
//                                try {
////                                    Log.e("data", queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
//                                     ddate = queryDocumentSnapshots.getDocuments().get(j).get("date").toString();
//                                     ttime = queryDocumentSnapshots.getDocuments().get(j).get("time").toString();
//                                     ssymp = queryDocumentSnapshots.getDocuments().get(j).get("symptoms").toString();
//                                     ddis_his = queryDocumentSnapshots.getDocuments().get(j).get("disease").toString();
//
//                                }
//                                catch(Exception e){
//                                    Log.e("err","error"+e);
//                                }
////                                Log.e("date og  ",""+date);
////                                Log.e("date dup  ",""+ddate);
////                                Log.e("time og  ",""+time);
////                                Log.e("ttime dup  ",""+ttime);
////                                Log.e("symp og  ",""+symp);
////                                Log.e("ssymp dup  ",""+ssymp);
////                                Log.e("dis_his og  ",""+dis_his);
////                                Log.e("ddhis dup  ",""+ddis_his);
//                                if (date.equals(ddate) || time.equals(ttime) || ssymp.equals(ssymp) || dis_his.equals(ddis_his)) {
//                                    Log.e("ignoringgg",""+symp+":"+ddis_his);
////                                    arrayList.add(new Custom_list_data(String.valueOf(i + 1), dis_his, symp, date, time));
//                                    Log.e("array list","exixts"+arrayList);
////                                    dup.add(queryDocumentSnapshots.getDocuments().get(j).getId());
////                                    og.remove(j);
//
//                                }
//                                else{
//
//                                }
//
//                            }
//                            arrayList.add(new Custom_list_data(String.valueOf(i + 1), dis_his, symp, date, time));
                            }
                        catch(Exception e){
                            Log.e("Exception",""+e);

                        }
                        //                        recent_uid.add(p_uid);
//                        symp.add(queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
                    }
//                    listView.setAdapter(a);
try {
    for (int jk = 0; jk < dddate.size(); jk++) {
//
//                        if(arrayList.get(i).getTime().equals(time)){
////                                Log.e("array list","exixts"+arrayList.get(i).getTime());
//                            Log.e("exiist","exixts"+time);
//                        }
//                        else {
        Log.e("adding", "" + dddisease.get(jk) + ":" + sssymp.get(jk) + ":" + dddate.get(jk) + ":" + tttime.get(jk));
        arrayList.add(new Custom_list_data(String.valueOf(jk + 1), dddisease.get(jk), sssymp.get(jk), dddate.get(jk), tttime.get(jk)));
//                            Log.e("array list","adding"+arrayList.get(i).getTime());
//                        }

    }
}
catch(Exception e){
    Log.e("errorrr","err"+e);
}


//                    Log.e("data", "adding in list" + arrayList);
                    customList = new CustomList(disea_history.this, arrayList);

                    lvv.setAdapter(customList);
                } else {
                    Toast.makeText(disea_history.this, "Erorr getting patient histoery", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    void remov_duplica(){
        ref.document(auth.getUid()).collection("disease").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot qq=task.getResult();
                    Log.e("query" , ""+qq.getDocuments().size());
                    for(int i=0;i<qq.getDocuments().size();i++ ){
//                        Log.e("....",".....................");
//                        Log.e("query" , ""+qq.getDocuments().get(i).get("symptoms"));
//                        Log.e("query" , ""+qq.getDocuments().get(i).get("disease"));
//                        Log.e("query" , ""+qq.getDocuments().get(i).get("date"));
//                        Log.e("query" , ""+qq.getDocuments().get(i).get("disease"));
//                        Log.e("....",".....................");

                        String date = String.valueOf(qq.getDocuments().get(i).get("date"));
                        String time = String.valueOf(qq.getDocuments().get(i).get("time"));
                        String symp = String.valueOf(qq.getDocuments().get(i).get("symptoms"));
                        String dis_his = String.valueOf(qq.getDocuments().get(i).get("disease"));

                        for(int j=0;j<qq.getDocuments().size();j++ ){
                            String ddate = String.valueOf(qq.getDocuments().get(j).get("date"));
                            String ttime = String.valueOf(qq.getDocuments().get(j).get("time"));
                            String ssymp = String.valueOf(qq.getDocuments().get(j).get("symptoms"));
                            String ddis_his = String.valueOf(qq.getDocuments().get(j).get("disease"));

                            if(dis_his.equals(ddis_his) && date.equals(ddate) && time.equals(ttime) && symp.equals(ssymp)){

                                Log.e("....",".....................");
                                Log.e("at indes",""+qq.getDocuments().get(i).getId());
                                Log.e("duplica","findddd ..."+i);
                                Log.e("duplica","findddd ..."+j);

                                Log.e("duplica","findddd ..."+dis_his+";"+ddis_his);
                                Log.e("duplica","findddd ..."+symp+";"+ssymp);
                                Log.e("duplica","findddd ..."+date+";"+ddate);
                                Log.e("duplica","findddd ..."+time+";"+ttime);
                                Log.e("....",".....................");


//                                ref.document(auth.getUid()).collection("disease").document(qq.getDocuments().get(j).getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//
//                                        Log.e("removed","removed");
//                                    }
//                                });


                            }
//                            else{
//                                Log.e("duplica","notttt foudddd");
//                            }



                        }




                    }



                }
            }
        });

    }








    //TODO:DELETE DUPlicate dataaaa here
// void past_dataaa() {
////        ArrayList<String> dup=new ArrayList<>();
//        ref.document(auth.getUid()).collection("disease").orderBy("time", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
////                    DocumentSnapshot d=task.getResult();
//                    QuerySnapshot queryDocumentSnapshots = task.getResult();
//                    Log.e("getting", "recent symptomps" + queryDocumentSnapshots.getDocuments().size());
//
//
//                    for (int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++) {
////                        dup.add();
////                        Log.e("id",""+queryDocumentSnapshots.getDocuments());
//                        try {
//
//
//                            Log.e("data", queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
//                            String date = queryDocumentSnapshots.getDocuments().get(i).get("date").toString();
//                            String time = queryDocumentSnapshots.getDocuments().get(i).get("time").toString();
//                            String symp = queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString();
//                            String dis_his = queryDocumentSnapshots.getDocuments().get(i).get("disease").toString();
////                            ref.document(auth.getUid()).collection("disease").document(dup.get(i)).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
////                                @Override
////                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
////                                    DocumentSnapshot sad=task.getResult();
////                                    String dddate =sad.get("date").toString();
////                                    String ddtime =sad.get("time").toString();
////                                    String ddsymp =sad.get("symptoms").toString();
////                                    String dddisea =sad.get("disease").toString();
////                                    if(task.isSuccessful()){
////                                        if(date == dddate && time == ddtime && symp == ddsymp && dis_his == dddisea){
////                                            ref.document(auth.getUid()).collection("disea").document().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
////                                                @Override
////                                                public void onComplete(@NonNull  Task<Void> task) {
////                                                    Log.e("logggg","removedddd sucessssss");
////                                                }
////                                            });
////                                        }
////
////                                    }
////                                    else{
////                                        Log.e("error","error");
////                                    }
////
////                                }
////                            });
//
//                            Log.e("dupliiiiiiii...........",""+dup.get(i));
//                            for(int jj=0;jj<dup.size();jj++) {
//                                int finalJj = jj;
//                                ref.document(auth.getUid()).collection("disease").document(dup.get(jj)).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                        if (task.isSuccessful()) {
//                                            DocumentSnapshot qq = task.getResult();
//                                            Log.e("data", "date:" + qq.get("date"));
//                                            if (qq.get("date").equals(date) && qq.get("time").equals(time)) {
//                                                Log.e("match", "found......");
//                                                ref.document(auth.getUid()).collection("disease").document(dup.get(finalJj)).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                    @Override
//                                                    public void onComplete(@NonNull  Task<Void> task) {
//                                                        Log.e("deleted","daaaattaa........");
//                                                    }
//                                                });
//
//                                            } else {
//                                                Log.e("nooo   match", " no found");
//                                            }
//
//
//                                        }
//                                    }
//                                });
//                            }
//                            Log.e("data", "adding in list");
//                            arrayList.add(new Custom_list_data(String.valueOf(i + 1), dis_his, symp, date, time));
//
//
//
//                        }
//                        catch(Exception e){
//                            Log.e("Exception",""+e);
//
//                        }
//                        //                        recent_uid.add(p_uid);
////                        symp.add(queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
//                    }
////                    listView.setAdapter(a);
//                    Log.e("data", "adding in list" + arrayList);
//                    customList = new CustomList(disea_history.this, arrayList);
//                    lvv.setAdapter(customList);
//                } else {
//                    Toast.makeText(disea_history.this, "Erorr getting patient histoery", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }
//


////
//void past_dupli() {
//
//
//    Log.e("id","passttttdupli....");
//        ref.document(auth.getUid()).collection("disease").orderBy("time", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull  Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.e("TAG", document.getId() + " => " + document.getData());
////                        dup.add(document.getId());
//
////                        for(int i=0;i<task.getResult().getDocuments().size();i++){
////                            if(document.getId() == dup.get(i)){
////
////                                Log.e("deleteing",""+document.getId());
////                                ref.document(auth.getUid()).collection("disease").document(document.getId()).delete();
////                            }
////                            else {
////                                Log.e("NOTHING","TO REMOVE");
////                            }
////                        }
//
//
//                    }
//
//
//                } else {
//                    Log.e("TAG", "Error getting documents.", task.getException());
//                }
//            }
//        });
//        past_dataaa();
//
////        ref.document(auth.getUid()).collection("disease").orderBy("time", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
////            @Override
////            public void onComplete(@NonNull Task<QuerySnapshot> task) {
////                if (task.isSuccessful()) {
//////                    DocumentSnapshot d=task.getResult();
////                    QuerySnapshot queryDocumentSnapshots = task.getResult();
////                    Log.e("getting", "recent symptomps" + queryDocumentSnapshots.getDocuments().size());
////
////
////                    for (int i = 0; i < queryDocumentSnapshots.getDocuments().size(); i++) {
//////                        dup.add();
////Log.e("id",""+queryDocumentSnapshots.getDocuments());
////
////                        try {
////                            Log.e("data", queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
////                            String date = queryDocumentSnapshots.getDocuments().get(i).get("date").toString();
////                            String time = queryDocumentSnapshots.getDocuments().get(i).get("time").toString();
////                            String symp = queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString();
////                            String dis_his = queryDocumentSnapshots.getDocuments().get(i).get("disease").toString();
////                            Log.e("data", "adding in list");
//////                            arrayList.add(new Custom_list_data(String.valueOf(i + 1), dis_his, symp, date, time));
////                        }
////                        catch(Exception e){
////                            Log.e("Exception",""+e);
////
////                        }
////                        //                        recent_uid.add(p_uid);
//////                        symp.add(queryDocumentSnapshots.getDocuments().get(i).get("symptoms").toString());
////                    }
//////                    listView.setAdapter(a);
////                    Log.e("data", "adding in list" + arrayList);
//////                    customList = new CustomList(disea_history.this, arrayList);
//////                    lvv.setAdapter(customList);
////                } else {
////                    Toast.makeText(disea_history.this, "Erorr getting patient histoery", Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
//
//    }

}