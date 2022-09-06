package com.example.hadop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {
    FloatingActionButton Signup;
    EditText name, email, pass, repass, age, phone,altph;
    RadioButton gender, catego;
    RadioGroup radioGroup, category;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference upload_data = firebaseFirestore.collection("data");
    Map<String, Object> data = new HashMap<>();
    String cate = "";
    String n, p, e, re, ph, ag,alt_p;
    int da, ca;
    String gen = "";
    View parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        parentLayout = findViewById(android.R.id.content);
        auth = FirebaseAuth.getInstance();
        Signup = findViewById(R.id.signup);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email_id);
        pass = findViewById(R.id.password);
        repass = findViewById(R.id.pass_re);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        altph = findViewById(R.id.altpho);
//        radiobutton=findViewById(R.id.signup);
        radioGroup = findViewById(R.id.gender);
        category = findViewById(R.id.category);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if (name.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Enter Name ", Snackbar.LENGTH_SHORT).show();
                } else if (email.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Enter Email ID ", Snackbar.LENGTH_SHORT).show();
                } else if (pass.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Enter Password ", Snackbar.LENGTH_SHORT).show();
                } else if (repass.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Re-Enter Password ", Snackbar.LENGTH_SHORT).show();
                } else if (!pass.getText().toString().equals(repass.getText().toString())) {
                    Snackbar.make(v, "Password Doesn't match ", Snackbar.LENGTH_SHORT).show();
                } else if (age.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Enter Age ", Snackbar.LENGTH_SHORT).show();
                } else if (phone.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Enter Phone No. ", Snackbar.LENGTH_SHORT).show();
                } else if (altph.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Enter Alternative No. ", Snackbar.LENGTH_SHORT).show();
                } else {
                    try {
                        Log.e("In", "gender");
                        da = radioGroup.getCheckedRadioButtonId();
                        gender = findViewById(da);
                        gen = gender.getText().toString();
                        try {
                            Log.e("In", "category");
                            ca = category.getCheckedRadioButtonId();
                            catego = findViewById(ca);

                            cate = catego.getText().toString();
                            upload_data();
                        } catch (Exception e) {
                            Log.e("In", "category error");
                            Snackbar.make(parentLayout, "Select Category ", Snackbar.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.e("In", "gender error");
                        Snackbar.make(parentLayout, "Select gender ", Snackbar.LENGTH_SHORT).show();
                    }


                }
//                else {
//                    try {
//                        Log.e("In","check");
//                        try {
//                            Log.e("In","gender radio");
//                            da = radioGroup.getCheckedRadioButtonId();
//                            gender = findViewById(da);
//
//                        }
//                        catch (Exception e){
//                            Log.e("In","gendre error"+e);
//                        Snackbar.make(parentLayout, "Gender Error "+e, Snackbar.LENGTH_SHORT).show();}
//                         try {
//                             Log.e("In","category check");
//                             ca = category.getCheckedRadioButtonId();
//                             catego = findViewById(ca);
//                        }
//                        catch (Exception e){    Log.e("In","category error"+e); Snackbar.make(parentLayout, "Category Error "+e, Snackbar.LENGTH_SHORT).show();}
//
//
//                    }
//                    catch (Exception e){   Log.e("In","all error"+e);
//                        Snackbar.make(parentLayout, "Error "+e, Snackbar.LENGTH_SHORT).show();
//                    }
//
//                    }

            }
        });

    }


    void upload_data() {


        n = name.getText().toString();
        p = pass.getText().toString();
        e = email.getText().toString();
        re = repass.getText().toString();
        ph = phone.getText().toString();
        ag = age.getText().toString();   alt_p = altph.getText().toString();
        try {
            gen = gender.getText().toString();
        } catch (Exception e) {
            Snackbar.make(parentLayout, "Sellect gender " + e, Snackbar.LENGTH_SHORT).show();
        }
        try {
            cate = catego.getText().toString();
        } catch (Exception e) {
            Snackbar.make(parentLayout, "Sellect Category " + e, Snackbar.LENGTH_SHORT).show();
        }


        data.put("name", n);
        data.put("email", e);
        data.put("phone", ph);
        data.put("age", ag);
        data.put("category", cate);
        data.put("gender", gen);data.put("alt number", alt_p);

        Log.e("data", "name : " + n);
        Log.e("data", "password : " + p);
        Log.e("data", "email : " + e);
        Log.e("data", "passre : " + re);
        Log.e("data", "age : " + ag);
        Log.e("data", "phone : " + ph);
        Log.e("data", "gender : " + gen);
        Log.e("data", "Category : " + cate);


        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(e, p).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String uid = auth.getCurrentUser().getUid();
                data.put("uid", uid);
                upload_data.document(uid).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Snackbar.make(parentLayout, "SignUp data uploaded", Snackbar.LENGTH_SHORT).show();
                    }
                });


                Log.e("uid", uid);

                switch (cate) {
                    case "Patient":
                        Intent i = new Intent(signup.this, patient.class);
                        startActivity(i);
                        break;
                    case "Doctor":
                        Intent ij = new Intent(signup.this, doctor.class);
                        startActivity(ij);
                        break;
                    default:
                        Snackbar.make(parentLayout, "ERROR", Snackbar.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(signup.this, "ERROR " + e, Toast.LENGTH_SHORT).show();
                Snackbar.make(parentLayout, "ERROR" + e, Snackbar.LENGTH_SHORT).show();
                Log.e("ERROR   ", e.toString());
            }
        });


    }
}

