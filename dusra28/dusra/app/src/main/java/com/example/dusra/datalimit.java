package com.example.dusra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class datalimit extends AppCompatActivity {
  private static final String TAG = "MainActivity";
  Button set;
  EditText setlimit;
  private FirebaseFirestore db = FirebaseFirestore.getInstance();
  private DocumentReference noteRef=db.document("data/datalimit");
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_datalimit);
    set = findViewById(R.id.datalimit);
    setlimit=findViewById(R.id.limit);
    set.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String setlimit1 = setlimit.getText().toString();
        Map<String, Object> note = new HashMap<>();
        note.put("limit", setlimit1);
        noteRef.set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(), "data saved", Toast.LENGTH_SHORT).show();
                  }
                })
                .addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, e.toString());
                  }
                });






      }
    });
  }
}
