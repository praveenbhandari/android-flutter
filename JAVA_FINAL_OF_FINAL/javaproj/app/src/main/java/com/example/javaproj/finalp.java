package com.example.javaproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class finalp extends AppCompatActivity {
ImageView i;
FirebaseAuth auth=FirebaseAuth.getInstance();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref=db.collection("data");
    TextView from,to,date,time,bname;
    TextView name,gend,phone,age;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalp);
        i=findViewById(R.id.img);
        name=findViewById(R.id.passengerp3);
        gend=findViewById(R.id.gende);
        phone=findViewById(R.id.pho);
        age=findViewById(R.id.ag);
         b =getIntent().getExtras();

        from=findViewById(R.id.dfromp3);
        to=findViewById(R.id.dtop3);
        date=findViewById(R.id.datep3);
        time=findViewById(R.id.dto3);
        bname=findViewById(R.id.busnumberp3);

        bname.setText(b.getString("name"));
        to.setText(b.getString("to"));
        from.setText(b.getString("from"));
        time.setText(b.getString("time"));
        date.setText(b.getString("date"));
        Log.e("time", b.getString("time"));

//        n=;
//        t=b.getString("time");
//        f=;
//        to=;
//        far=;
//        seat=b.getString("seats");


getdat();
        generate(auth.getUid());











    }
    void generate(String text) {
        Log.e("generate", "In");
//        String text = "praveeen";// Whatever you need to encode in the QR code
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            i.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        Log.e("generate", "done");
    }


    void getdat(){
        Log.e("gettinf","data");
//    List finaldest = new ArrayList();
//    List finalname = new ArrayList();
//    List finalfare = new ArrayList();
//    List finaltime = new ArrayList();
//    List finalfrom = new ArrayList();
        ref.document(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot d=task.getResult();
                    if(d.exists()){
                        name.setText(d.getData().get("name").toString());
                        gend.setText(d.getData().get("gender").toString());
                        age.setText(d.getData().get("age").toString());
                        phone.setText(d.getData().get("phone").toString());

                    }

                }

            }
        });
//        ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
////                Log.e("gettinf","dataaaaaaaa");
//                if (task.isSuccessful()) {
//                    Log.e("dessss","........");
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.e("dataaa", document.getId() + " => " + document.getData().get("name"));
//
////                        Log.e("store",""+ name);
//
//                    }
//                } else {
//                    Log.e("dataaa", "Error getting documents.", task.getException());
//                }
//                bname.setText(b.getString("name"));
//                time.setText(b.getString("time"));
//                to.setText(b.getString("to"));
//                from.setText(b.getString("from"));
////                date.setText("date");
//
//            }
//        });

    }










//    void popupw(Bitmap bitmap) {
//        Log.e("pop up", "In");
////        imageView=dialog.findViewById(R.id.image_v);
//        dialog.setContentView(R.layout.qr_display);
//        imageView = dialog.findViewById(R.id.img);
//        imageView.setImageBitmap(bitmap);
//
//        TextView x = dialog.findViewById(R.id.x);
//        x.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//
//
//    }
}