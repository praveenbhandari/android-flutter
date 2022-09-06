package com.example.javaproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class detail extends AppCompatActivity {
TextView dbname,dfare,dtime,dfrom,dto,se;
String n,t,f,to,far,seat,date,docid;
FloatingActionButton checkout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref=db.collection("bdata");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dbname=findViewById(R.id.dbname);
        dfare=findViewById(R.id.dfar);
        dtime=findViewById(R.id.dtim);
        dfrom=findViewById(R.id.dfrom);
        dto=findViewById(R.id.dto);
        se=findViewById(R.id.seat);
        checkout=findViewById(R.id.checkout);
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
        Bundle b= getIntent().getExtras();
        n=b.getString("name");
        t=b.getString("time");
        f=b.getString("from");
        to=b.getString("to");
        far=b.getString("fare");
        seat=b.getString("seats");
        date=b.getString("date");
docid=b.getString("doc");
Log.e("doc","id "+ docid);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.document(docid).update("avai",String.valueOf(Integer.parseInt(seat)-1)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.e("...","updated");
                    }
                });





                Intent i = new Intent(detail.this,finalp.class);
                i.putExtra("name", n);
                i.putExtra("to", to);
                i.putExtra("fare", far);
                i.putExtra("time", t);
                i.putExtra("from", f);
                i.putExtra("date", date);
                startActivity(i);
            }
        });


//        ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
////                Log.e("gettinf","dataaaaaaaa");
//                if (task.isSuccessful()) {
//                    Log.e("dessss","");
//                    for (QueryDocumentSnapshot document : task.getResult()) {
////                        Log.e("dataaa", document.getId() + " => " + document.getData().get("name"));
//                        seat=document.getData().get("avai").toString();
//                        Log.e("seat",""+ seat);
//
//                    }
//                } else {
//                    Log.e("dataaa", "Error getting documents.", task.getException());
//                }
//
//            }
//        });
        dbname.setText(n);
        dfare.setText("₹ "+far+"/-");
        dtime.setText(t);
        dfrom.setText(f);
        dto.setText(to);
        se.setText("Available seats : "+seat);









//        tt.setText(b.getString("name")+"\n"+b.getString("desti")+"\n"+b.getString("fare")+"\n"+b.getString("time"));

//        try {
//
//
//            Document doc = Jsoup.connect("https://paytm.com/bus-tickets").get();
//            String title = doc.html();
//            Log.e("title is: ","" + title);
//
////            URL u=new URL("https://www.redbus.in/");
////            URLConnection con=  u.openConnection();
////            InputStream stream=con.getInputStream();
////            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
////            String line=null;
////            while ((line = br.readLine()) != null) {
//////                System.out.println(line);
////                Log.e("sitee",""+line);
////            }
////            stream.close();
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}