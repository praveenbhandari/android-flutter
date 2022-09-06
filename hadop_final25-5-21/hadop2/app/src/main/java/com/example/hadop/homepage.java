package com.example.hadop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class homepage extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    String name, email, phone, gender, age, category, uid;
    CollectionReference patient,ref;
    CollectionReference doc;
    FloatingActionButton f;
    Bundle b;
    String activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
f=findViewById(R.id.floating);
        FirebaseAuth auth=FirebaseAuth.getInstance();
        uid = auth.getUid();
        patient = firebaseFirestore.collection("Patient");
        doc = firebaseFirestore.collection("Doctor");
f.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Snackbar.make(v,"Clicked button",Snackbar.LENGTH_SHORT).show();
    }
});
//        Collection erf=firebaseFirestore.collection("asd");

//        t1 = findViewById(R.id.t1);
//         t2 = findViewById(R.id.t2);
//         t3 = findViewById(R.id.t3);
//         t4 = findViewById(R.id.t4);
//         t5 = findViewById(R.id.t5);

       b = getIntent().getExtras();
       activity=b.getString("activity");
       Log.e("activty",activity);
//       getdata();
//check();

       Log.e("done","check");

//       upload();

//        doc.document(uid).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(homepage.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(homepage.this, "Error", Toast.LENGTH_SHORT).show();
//            }
//        });


    }



//    void check() {
//
//        if(  activity == "login"){
//            Log.e("in if","login");
//            getdata();
//        }
//        else if( activity == "signup"){
//            Log.e("in if","signup");
//            upload();
//        }
//    }
    void upload() {

//        uid = b.getString("uid");
        name = b.getString("name");
        email = b.getString("email");
        phone = b.getString("phone");
        age = b.getString("age");
        category = b.getString("category");
        gender = b.getString("gender");
        Log.e("in home", uid + name + email + phone + age + category + gender);
//        t.setText(b.getString("uid"));


        Map<String, Object> data = new HashMap<>();
        data.put("uid", uid);
        data.put("name", name);
        data.put("email", email);
        data.put("phone", phone);
        data.put("age", age);
        data.put("category", category);
        data.put("gender", gender);

firebaseFirestore.collection("data").document(uid).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
    @Override
    public void onSuccess(Void aVoid) {
        Log.e("ERROR","DONE");
        Toast.makeText(homepage.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
    }
}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(homepage.this, "Error", Toast.LENGTH_SHORT).show();
        Log.e("error","error");
    }
});
//        if (category == "Patient") {
//            patient.document(uid).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    Toast.makeText(homepage.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(homepage.this, "Error", Toast.LENGTH_SHORT).show();
//                }
//            });
//            ;
//
//
//        }
//        else if (category == "Doctor") {
//            doc.document(uid).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    Toast.makeText(homepage.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(homepage.this, "Error", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }
    }
//
//    void  getdata() {
//        Log.e("in getdata","dataaata");
//        FirebaseFirestore.getInstance().collection("Doctor").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.e("snapshot","INSNAPSHOT");
//                        Log.e("data",document.getString("age") + document.getString("email"));
//                        String a=document.get("age").toString();
//                        String b=document.get("email").toString();
//                        Log.e("a",a);
//                        Log.e("b",b);
//                        t1.setText(a);
//                        t2.setText(b);
//                        Log.e("TAG", document.getId() + " => " + document.getData());
//                    }
//                } else {
//                    Log.w("TAG", "Error getting documents.", task.getException());
//                }
//            }
//        });
//    }
}
