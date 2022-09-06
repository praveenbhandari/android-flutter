package com.example.dusra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button btnLogin,newuser;
String email,pass;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();



newuser=findViewById(R.id.button);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String abc=e1.getText().toString();
//                String ab=e2.getText().toString();
//                if(e1.length()==0)
//
//                {
//                    e1.requestFocus();
//                    e1.setError("FIELD CANNOT BE EMPTY");
//                }
//
//                else if(!abc.matches("[a-zA-Z ]+"))
//                {
//                    e1.requestFocus();
//                    e1.setError("ENTER ONLY ALPHABETICAL CHARACTER");
//                }
//
//                else if(ab.length()==0)
//                {
//                    e2.requestFocus();
//                    e2.setError("FIELD CANNOT BE EMPTY");
//                }
//
//
//                else
//                {
                    email=e1.getText().toString();
                    pass=e2.getText().toString();

                    if (!email.equals("") && !pass.equals("") ){
                        // mAuth.signInWithCredential(email1,pass1);
                        mAuth.signInWithEmailAndPassword(email,pass)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()) {
                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                           // String username=user.getUid();
                                            Toast.makeText(MainActivity.this, "logged in", Toast.LENGTH_SHORT).show();
                                            Intent i=new Intent(MainActivity.this,home.class);
                                            startActivity(i);
                                        }else{
                                            Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }


            //}
        });

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = e1.getText().toString();
                pass = e2.getText().toString();
                if (!e1.equals("") && !pass.equals("")) {
                    mAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid=user1.getUid();
                                        DocumentReference k=    db.collection(uid).document("kyc");
                                        Map<String, Object> userdata = new HashMap<>();
                                        userdata.put("Name", "");
                                        userdata.put("Address", "");
                                        userdata.put("City", "");
                                        userdata.put("Pin", "");
                                        userdata.put("Phone no", "");
                                        db.collection(uid).document("kyc")
                                                .set(userdata);
                                        DocumentReference usa=    db.collection(uid).document("data usage");

                                        Log.e("NULLLLLL usage", "NULLLLL");
                                        Map<String, Object> usage = new HashMap<>();
                                        usage.put("limit", "");
                                        usage.put("usage", "");
                                        db.collection(uid).document("data usage")
                                                .set(usage);

                                        DocumentReference lim=    db.collection(uid).document("data limit");


                                        Log.e("NULLLLLL", "NULLLLL");
                                        Map<String, Object> limit = new HashMap<>();
                                        limit.put("limit", "");


                                        db.collection(uid).document("data limit")
                                                .set(limit);


                                        DocumentReference pa=    db.collection(uid).document("Pacage");

                                        Log.e("NULLLLLL package", "NULLLLL");
                                        Map<String, Object> pack = new HashMap<>();
                                        pack.put("expiry", "NULL");
                                        pack.put("package name", "NULL");
                                        pack.put("package type", "NULL");
                                        pack.put("rate", "NULL");
                                        db.collection(uid).document("Package")
                                                .set(pack);



//                                        FirebaseFirestore db = FirebaseFirestore.getInstance();
//                                        Map<String, Object> userdata = new HashMap<>();
//                                        userdata.put("name", "");
//                                        userdata.put("address", "");
//                                        userdata.put("City", "");
//                                        userdata.put("Pin", "");
//                                        userdata.put("Phone no", "");
//                                        db.collection(uid).document("data")
//                                                .set(userdata)
//                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                    @Override
//                                                    public void onSuccess(Void aVoid) {
//                                                        Log.d(TAG, "DocumentSnapshot added  ");
//                                                    }
//                                                })
//                                                .addOnFailureListener(new OnFailureListener() {
//                                                    @Override
//                                                    public void onFailure(@NonNull Exception e) {
//                                                        Log.w(TAG, "Error adding document", e);
//                                                    }
//                                                });
                                        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                     //   String username=user.getUid();

                                        Intent i=new Intent(MainActivity.this,home.class);
//                                        i.putExtra("userid",username);
                                        startActivity(i);
                                        Toast.makeText(MainActivity.this, "Successfully regestered", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
