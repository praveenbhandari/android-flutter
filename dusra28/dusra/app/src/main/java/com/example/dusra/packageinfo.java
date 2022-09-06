package com.example.dusra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class packageinfo extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference noteRef=db.document("user/user1");
    //private CollectionReference notebookRef = db.collection("user");
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packageinfo);


        final TextView name= findViewById(R.id.name);
        final TextView nameoforg= findViewById(R.id.nameoforg);
        final TextView address= findViewById(R.id.address);
        final TextView city= findViewById(R.id.city);
        final TextView pin= findViewById(R.id.pin);
        final TextView phoneno= findViewById(R.id.phone);
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if
                        (documentSnapshot.exists()) {
                            String name11 = documentSnapshot.getString("Name");
                            String nameoforg11 = documentSnapshot.getString("Nameoforg");
                            String city11 = documentSnapshot.getString("City");
                            String address11 = documentSnapshot.getString("Address");
                            String pin11 = documentSnapshot.getString("Pin");
                            String phone11 = documentSnapshot.getString("Phone no");

                            // double p=Integer.parseInt(pin11);

                            //double ph=Integer.parseInt(phone11);
                            name.setText(name11);
                            nameoforg.setText(nameoforg11);
                            address.setText(address11);
                            city.setText(city11);
                            pin.setText(pin11);
                            phoneno.setText(phone11);




                            //Map<String, Object> note = documentSnapshot.getData();

                           /* loaddata.setText("Name= " + name11 + "\n" + "Password= " + nameoforg11 + "\n" + "Address= " + address11 + "\n"
                                    + "City= " + city11 + "\n" + "Pin= " + pin11 + "\n" + "Phoneno= " + phone11);*/
                        } else {
                            Toast.makeText(getApplicationContext(), "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
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


}



       /* notebookRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String data = "";

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Note note = documentSnapshot.toObject(Note.class);
                            note.setDocumentId(documentSnapshot.getId());

                            String documentId = note.getDocumentId();
                            String name = note.getName();
                            String pass = note.getPassword();

                            data += "ID: " + documentId
                                    + "\nname: " + name + "\npassword: " + pass + "\n\n";
                        }

                        loaddata.setText(data);
                    }
                });
*/












