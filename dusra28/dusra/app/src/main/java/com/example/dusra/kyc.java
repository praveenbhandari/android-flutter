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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class kyc<override> extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORD = "password";
    private static final int PICK_IMAGE_REQUEST = 234;
    private StorageReference mStorageRef;

    private EditText name, nameoforg, address, city, phone, pin;
public Uri uri;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference noteRef = db.document("user/user1");
    //private CollectionReference notebookRef = db.collection("user");

    double p;
    double ph;
    Button b1;

    ImageView iv1;

    Note note;
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
        mStorageRef = FirebaseStorage.getInstance().getReference("image");
        name = findViewById(R.id.name);
        nameoforg = findViewById(R.id.nameoforg);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        phone = findViewById(R.id.phone);
        pin = findViewById(R.id.pin);

        iv1=findViewById(R.id.iv1);

        final Button save = findViewById(R.id.kyc);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileupload();
                String name11 = name.getText().toString();
                String nameoforg11 = nameoforg.getText().toString();
                String address11 = address.getText().toString();
                String city11 = city.getText().toString();
                String pin11 = pin.getText().toString();
                String phone11 = phone.getText().toString();
                //p = Integer.parseInt(pin11);
                //ph = Integer.parseInt(phone11);
                Map<String, Object> note = new HashMap<>();
                note.put("Name", name11);
                note.put("Name of org", nameoforg11);
                note.put("Address", address11);
                note.put("City", city11);
                note.put("Pin", pin11);
                note.put("Phone no", phone11);


                //note = (Map<String, Object>) new Note(name11, nameoforg11);

               noteRef.set(note)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(kyc.this, "Data saved", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(kyc.this, "Error!", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, e.toString());
                            }
                        });
                //save.setClickable(false);
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
