package com.example.dusra;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

import static android.hardware.camera2.params.RggbChannelVector.RED;

public class datausage extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public int p,q,left;
    String limit = "10";
    String used="70";
    //String[] uses={"jan","feb","mar"};
  //AnyChartView anyChartView;
//int[] earnings={500,800,2000};
private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference noteRef=db.document("data/datalimit");
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_datausage);
    PieChartView pieChartView = findViewById(R.id.chart);


      noteRef.get()
              .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                  @Override
                  public void onSuccess(DocumentSnapshot documentSnapshot) {
                      if
                      (documentSnapshot.exists()) {
                          limit = documentSnapshot.getString("limit");
                          used = documentSnapshot.getString("used");

                       //  p=Integer.parseInt(limit);
                       //  q=Integer.parseInt(used);
                         // left=q-p;

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

int l=Integer.parseInt(limit);

int u=Integer.parseInt(used);
      int a=l-u;
    ArrayList<SliceValue> pieData = new ArrayList<>();
    pieData.add(new SliceValue(a, Color.GREEN).setLabel("DATA USED"));
    pieData.add(new SliceValue(u, Color.RED).setLabel("DATA LEFT"));
    PieChartData pieChartData = new PieChartData(pieData);
    pieChartData.setHasLabels(true).setValueLabelTextSize(14);
    pieChartData.setHasCenterCircle(true).setCenterText1("DATA USAGE").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));

    pieChartView.setPieChartData(pieChartData);

    }

}

