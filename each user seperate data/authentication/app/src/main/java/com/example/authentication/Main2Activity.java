package com.example.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
EditText name,addr;
Button submit,retrive;
TextView nam,add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=findViewById(R.id.editText3);
        addr=findViewById(R.id.editText4);
        submit=findViewById(R.id.button3);
        nam=findViewById(R.id.textView2);
        add=findViewById(R.id.textView3);
        retrive=findViewById(R.id.button4);
     Intent i=getIntent();
     //final String uid=i.getStringExtra("userid");
     //add.setText(uid);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1,addr1;
                name1=name.getText().toString();
                addr1=addr.getText().toString();
                if(!name1.equals("") && !addr1.equals("")) {
                 //   if (uid.isEmpty()) {

                        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                        String uid=user1.getUid();
// Create a new user with a first and last name
                        Map<String, Object> user = new HashMap<>();
                        user.put("name", name1);
                        user.put("address", addr1);

// Add a new document with a generated ID
                        db.collection(uid).document("data")
                                .set(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot added  ");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error adding document", e);
                                    }
                                });
                    }
                }
          //  }
        });
        retrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid=user.getUid();
                DocumentReference docref=    db.collection(uid).document("data");
            docref.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document= task.getResult();
                                    if (document.exists()) {
                                        String address11=document.getString("address");
                                        String name111=document.getString("name");
                                        nam.setText(name111);
                                        add.setText(address11);


                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                    }
                                } else {
                                    Log.w(TAG, "Error getting documents.", task.getException());
                                }
                            }
                        });
            }
        });


    }
}
