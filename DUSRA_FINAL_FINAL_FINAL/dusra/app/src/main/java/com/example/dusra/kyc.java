package com.example.dusra;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class kyc<override> extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

   // private static final String KEY_NAME = "name";
    //private static final String KEY_PASSWORD = "password";
    //private static final int PICK_IMAGE_REQUEST = 234;
    private StorageReference mStorageRef;

    private EditText namedata,  addressdata, citydata, phonedata, pindata;
    public Uri uri;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
  //  private DocumentReference noteRef = db.document("user/user1");
    //private CollectionReference notebookRef = db.collection("user");


    Button b1;

    ImageView iv1;

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();
            iv1.setImageURI(uri);

            /*try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = findViewById(R.id.iv1);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc);
        b1=findViewById(R.id.bt1);

        namedata = findViewById(R.id.name);
       // nameoforg = findViewById(R.id.nameoforg);
        addressdata = findViewById(R.id.address);
        citydata = findViewById(R.id.city);
        phonedata = findViewById(R.id.phone);
        pindata = findViewById(R.id.pin);




        iv1=findViewById(R.id.iv1);

        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
        String uid=user1.getUid();
        DocumentReference docref=    db.collection(uid).document("kyc");

        docref.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document= task.getResult();
                            if (document.exists()) {
                                String address11=document.getString("Address");
                                String name111=document.getString("Name");
                                String city = document.getString("City");
                                String pin = document.getString("Pin");

                                String phone = document.getString("Phone no");

                                namedata.setText(name111);
                                addressdata.setText(address11);
                                citydata.setText(city);
                                pindata.setText(pin);
                                phonedata.setText(phone);


                                Toast.makeText(getApplicationContext(), "Getting data if exist", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error getting documents.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


        final Button save = findViewById(R.id.kyc);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileupload();
                String pin11 = pindata.getText().toString();
                String phone11 = phonedata.getText().toString();
                String name11 = namedata.getText().toString();


                Map<String, Object> userdata = new HashMap<>();
                userdata.put("Phone no", phone11);
                userdata.put("Pin", pin11);

                String address11 = addressdata.getText().toString();
                String city11 = citydata.getText().toString();

                FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                String uid=user1.getUid();
// Create a new
                userdata.put("Name", name11);
                userdata.put("Address", address11);
                userdata.put("City", city11);



// Add a new document with a generated ID
                db.collection(uid).document("kyc")
                        .set(userdata)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(getApplicationContext(), "Error adding data", Toast.LENGTH_SHORT).show();
                            }
                        });

                //String nameoforg11 = nameoforg.getText().toString();
                //p = Integer.parseInt(pin11);
                //ph = Integer.parseInt(phone11);
//                Map<String, Object> note = new HashMap<>();
//                note.put("Name", name11);
//                //note.put("Name of org", nameoforg11);
//                note.put("Address", address11);
//                note.put("City", city11);
//                note.put("Pin", pin11);
//                note.put("Phone no", phone11);



//
//                //note = (Map<String, Object>) new Note(name11, nameoforg11);
//
//                noteRef.set(note)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(kyc.this, "Data saved", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(kyc.this, "Error!", Toast.LENGTH_SHORT).show();
//                                Log.d(TAG, e.toString());
//                            }
//                        });
//                save.setClickable(false);
            }


        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseImage();
            }


        });
    }
    private void fileupload() {
        mStorageRef = FirebaseStorage.getInstance().getReference("image");
        StorageReference riversRef = mStorageRef.child(System.currentTimeMillis()+","+getExtension(uri));

        riversRef.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(getApplicationContext(),"successfully uploaded",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });

    }
    private String getExtension(Uri uri){
        ContentResolver cr= getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
