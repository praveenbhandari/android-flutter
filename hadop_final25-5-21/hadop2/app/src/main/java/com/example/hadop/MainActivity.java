package com.example.hadop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.errorprone.annotations.Var;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

public class MainActivity extends AppCompatActivity {
    Button login, signup;
    EditText email, pass;
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference ref = firebaseFirestore.collection("data");
    String category_check;
    FirebaseUser user;
    String email_1, pass_1;
    View parentLayout;
    ConnectivityManager connectivityManager;
//    Dialog dialog;
    ProgressDialog progressDialog;
    ProgressBar progressBar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//progressBar=findViewById(R.id.progress);
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        parentLayout = findViewById(android.R.id.content);
//        dialog = new Dialog(this);
//        progressBar.setIndeterminate(true);
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

check_category_in_login();
//        asynTest.execute();
//        check_category_in_login();
//        if (connectivityManager.getActiveNetworkInfo() == null) {
//            Log.e("in", "connection check");
//            Snackbar.make(parentLayout, "NO INTERNET CONNECTION", Snackbar.LENGTH_INDEFINITE).setBackgroundTint(Color.RED).setAction("OK", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    check_Cconnection();
//                }
//            }).setActionTextColor(Color.YELLOW).show();
//            email.setEnabled(false);
//            pass.setEnabled(false);
//            login.setEnabled(false);
//            signup.setEnabled(false);
//        } else {
//            email.setEnabled(true);
//            pass.setEnabled(true);
//            login.setEnabled(true);
//            signup.setEnabled(true);
//        }

//        if (user != null) {
//            Log.e("user exists", "exists" + user.getEmail());
//            check_category_in_login();
//        } else {
//            Log.e("user ", " no user");
//        }


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                popupw(View.GONE);

                Log.e("onclick", "going to Signup");

                Intent i = new Intent(MainActivity.this, signup.class);
                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final asynTest asynTest=new asynTest();
//                asynTest.execute();
//                popupw(View.VISIBLE);
//                progressBar.setVisibility();
//                progressBar.setIndeterminate(true);
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
//                            getData();
//                            ref.document(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                    if (task.isSuccessful()) {
//                                        DocumentSnapshot document = task.getResult();
//                                        if (document.exists()) {
//                                            Log.e("TAG", "DocumentSnapshot data: " + document.getString("category"));
//                                            category_check = document.getString("category");
//                                            if (category_check != null) {
//                                                switch (category_check) {
//                                                    case "Patient":
//                                                        Intent i = new Intent(MainActivity.this, patient.class);
//                                                        startActivity(i);
//                                                        break;
//                                                    case "Doctor":
//                                                        Intent ij = new Intent(MainActivity.this, doctor.class);
//                                                        startActivity(ij);
//                                                        break;
//                                                    default:
//                                                        Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
//                                                }
//                                            }
//
//                                        } else {
//                                            Snackbar.make(parentLayout, "Error  " , Snackbar.LENGTH_INDEFINITE).show();
//                                            Log.e("TAG", "No such document");
//                                        }
//
//                                    }
//                                }
//                            }).addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    Snackbar.make(parentLayout, "Error  " + e, Snackbar.LENGTH_INDEFINITE).show();
//                                }
//                            });
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
//    void popupw(int vie) {
//        Log.e("pop up", "In");
////        imageView=dialog.findViewById(R.id.image_v);
//        dialog.setContentView(R.layout.progresss);
//        progressBar=dialog.findViewById(R.id.progress);
//        progressBar.setVisibility(vie);
////        imageView = dialog.findViewById(R.id.img);
////        imageView.setImageBitmap(bitmap);
//
////        TextView x = dialog.findViewById(R.id.x);
////        x.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                dialog.dismiss();
////            }
////        });
//        dialog.show();
//
//
//    }
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

    void check_category_in_login() {
        progressDialog.setMessage("Checking Credentials");

        progressDialog.show();
        Log.e("TAG", "DocumentSnapshot data: ");
        if (user != null) {
            Log.e("user exists", "exists" + user.getEmail());
            getData();
        } else {
            progressDialog.hide();
            Log.e("user ", " no user");
        }



    }
void getData(){
    ref.document(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.e("TAG", "DocumentSnapshot data: " + document.getString("category"));
                    category_check = document.getString("category");
                    if (category_check != null) {
                        switch (category_check) {
                            case "Patient":

                                Intent i = new Intent(MainActivity.this, patient.class);
                                startActivity(i);
                                break;
                            case "Doctor":progressDialog.hide();
                                Intent ij = new Intent(MainActivity.this, doctor.class);
                                startActivity(ij);
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
//                            progressDialog.hide();
                    Log.e("TAG", "No such document");
                }

            }
        }
    });
}

    private class asynTest extends AsyncTask<String,String,String>{

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
           getData();
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

