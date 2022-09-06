package com.example.javaproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    Button login, signup;
    EditText email, pass;
    FirebaseAuth auth;
//    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
//    CollectionReference ref = firebaseFirestore.collection("users");

    FirebaseUser user;
    String email_1, pass_1;
//    View parentLayout;
    ConnectivityManager connectivityManager;
//    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        Log.e("in create", "oncrera");

        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        check_Cconnection();

        Log.e("TAG", "DocumentSnapshot data: ");
        if (user != null) {
            Log.e("user exists", "exists" + user.getEmail());
            Intent i =new Intent(MainActivity.this,main.class);
            startActivity(i);
        } else {
//            progressDialog.hide();
            Log.e("user ", " no user continuing");
        }
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
                            Snackbar.make(v,"Logging in",Snackbar.LENGTH_SHORT).setBackgroundTint(Color.RED).show();
                            Intent i = new Intent(MainActivity.this, main.class);
                            startActivity(i);
//                            if(task.isSuccessful()) {
//                                asynTest asynTest = new asynTest();
//                                asynTest.execute();
//                            }
//                            else{
//                                Snackbar.make(parentLayout, "Error Logging in  check Email or Password ", Snackbar.LENGTH_SHORT).setBackgroundTint(Color.RED).show();
//                                Log.e("ERROR","ERROR");
//                            }
                        }
                    });

                }


            }
        });



    }

    private void check_Cconnection() {
    }
}