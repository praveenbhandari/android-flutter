package com.example.pyproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

public class MainActivity extends AppCompatActivity {
    Button login, signup;
    EditText email, pass;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference ref = firebaseFirestore.collection("users");

    FirebaseUser user;
    String email_1, pass_1;
    View parentLayout;
    ConnectivityManager connectivityManager;
    ProgressDialog progressDialog;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        parentLayout = findViewById(android.R.id.content);
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        Log.e("in create", "oncrera");

        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        progressDialog =new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(true);
        check_Cconnection();

        Log.e("TAG", "DocumentSnapshot data: ");
        if (user != null) {
            Log.e("user exists", "exists" + user.getEmail());
            Intent i =new Intent(MainActivity.this,disea_history.class);
            startActivity(i);
        } else {
            progressDialog.hide();
            Log.e("user ", " no user");
        }


//        check_category_in_login();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("onclick", "going to Signup");

                Intent i = new Intent(MainActivity.this, signup.class);
                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("onclick", "in Login");

                email_1 = email.getText().toString();
                pass_1 = pass.getText().toString();

                if (email_1.isEmpty()) {
                    Snackbar.make(v, "Enter Email Id ", Snackbar.LENGTH_SHORT).setBackgroundTint(Color.RED).show();
                } else if (pass_1.isEmpty()) {
                    Snackbar.make(v, "Enter Password ", Snackbar.LENGTH_SHORT).setBackgroundTint(Color.RED).show();
                } else {
                    auth.signInWithEmailAndPassword(email_1, pass_1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                asynTest asynTest = new asynTest();
                                asynTest.execute();
                                auth = FirebaseAuth.getInstance();
                            }
                            else{
                                Snackbar.make(parentLayout, "Error Logging in  check Email or Password ", Snackbar.LENGTH_SHORT).setBackgroundTint(Color.RED).show();
                                Log.e("ERROR","ERROR");
                            }
                        }
                    });

                }


            }
        });


    }

    void check_Cconnection() {
        if (connectivityManager.getActiveNetworkInfo() == null) {
            Log.e("in", "connection check");
            Snackbar.make(parentLayout, "NO INTERNET CONNECTION", Snackbar.LENGTH_INDEFINITE).setBackgroundTint(Color.RED).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    check_Cconnection();
                }
            }).setActionTextColor(Color.YELLOW).show();
            email.setEnabled(false);
            pass.setEnabled(false);
            login.setEnabled(false);
            signup.setEnabled(false);
        } else {
            email.setEnabled(true);
            pass.setEnabled(true);
            login.setEnabled(true);
            signup.setEnabled(true);
        }
    }

//    void check_category_in_login() {
//        progressDialog.setMessage("Checking Credentials");
//
//        progressDialog.show();
//        Log.e("TAG", "DocumentSnapshot data: ");
//        if (user != null) {
//            Log.e("user exists", "exists" + user.getEmail());
//            getData();
//        } else {
//            progressDialog.hide();
//            Log.e("user ", " no user");
//        }
//
//
//
//    }
//    void getData(){
//        ref.document(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.e("TAG", "DocumentSnapshot data: " + document.getString("category"));
//                        category_check = document.getString("category");
//                        if (category_check != null) {
//                            switch (category_check) {
//                                case "Patient":
//
//                                    Intent i = new Intent(MainActivity.this, patient.class);
//                                    startActivity(i);
//                                    break;
//                                case "Doctor":progressDialog.hide();
//                                    Intent ij = new Intent(MainActivity.this, doctor.class);
//                                    startActivity(ij);
//                                    break;
//                                default:
//                                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                    } else {
////                            progressDialog.hide();
//                        Log.e("TAG", "No such document");
//                    }
//
//                }
//            }
//        });
//    }

    private class asynTest extends AsyncTask<String,String,String> {

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("in"," preexecute async");
            progressDialog =new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("in","post execute async");
            super.onPostExecute(s);

            progressDialog.hide();
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.e("in","background async");
            ref.document(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull  Task<DocumentSnapshot> task) {
                    DocumentSnapshot dd=task.getResult();
                    dd.get("category");
                    if(dd.get("category").equals("Doctor")){
                        Intent i =new Intent(MainActivity.this,doctor.class);
                        startActivity(i);
                    }else if(dd.get("category").equals("Patient")){
                        Intent i =new Intent(MainActivity.this,disea_history.class);
                        startActivity(i);
                    }
                    else{
                        Log.e("error","e");
                    }

                }
            });



//            Intent i =new Intent(MainActivity.this,disea_history.class);
//            startActivity(i);
//            getData();
//            Thread t=new Thread();
//            try {
//                int t=5000;
//                progressDialog.setMessage(String.valueOf(t));
//                Thread.sleep(t);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            return null;
        }
    }
}