package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase.Model.Data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    private EditText userTextView, passwordTextView,emailfield;
    private Button Btn;
    private TextView text1;
    private FirebaseAuth mAuth;
    private DatabaseReference mdatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth=FirebaseAuth.getInstance();
        mdatabase= FirebaseDatabase.getInstance().getReference().child("Userdata");
               Registration();
    }
    private void Registration()
    {
        userTextView=findViewById(R.id.username_reg_xml);
        passwordTextView=findViewById(R.id.password_reg_xml);
        emailfield=findViewById(R.id.email_reg_xml);
        Btn=findViewById(R.id.login);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             final String username=userTextView.getText().toString().trim();
             final  String pass=passwordTextView.getText().toString().trim();
             final String email=emailfield.getText().toString().trim();
             if(TextUtils.isEmpty(username))
             {
                 userTextView.setError("Required Field");
                 return;
             }
             if(TextUtils.isEmpty(pass))
             {
               passwordTextView.setError("Required Field");
               return;
             }
                if(TextUtils.isEmpty(email))
                {
                    emailfield.setError("Required Field");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(username,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            String id =mdatabase.push().getKey();
                            Data data=new Data(username, email, pass,id);
                         mdatabase.child(id).setValue(data);
                            Toast.makeText(getApplicationContext(),"Registration success",Toast.LENGTH_LONG).show();
                        }else
                        {
                            Toast.makeText(getApplicationContext(),"Registration  not success",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

    }
}
