package com.example.wireless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private final int REQ_CODE = 100;
    TextView textView;
    Switch aSwitch;
    Switch bSwitch;
    Switch cSwitch;
    Switch dSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch= findViewById(R.id.switch1);
        bSwitch= findViewById(R.id.switch2);
        cSwitch=findViewById(R.id.switch3);
        dSwitch= findViewById(R.id.switch4);
        textView = findViewById(R.id.text);
        ImageView speak = findViewById(R.id.speak);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");
                try {
                    startActivityForResult(intent, REQ_CODE);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry your device not supported",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true){
                    Toast.makeText(getBaseContext(),"ON",Toast.LENGTH_SHORT).show();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("S1");

                    myRef.setValue("1");
                }else {
                    Toast.makeText(getBaseContext(),"OFF",Toast.LENGTH_SHORT).show();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("S1");

                    myRef.setValue("0");
                }
            }


        });
        bSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true){
                    Toast.makeText(getBaseContext(),"ON",Toast.LENGTH_SHORT).show();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("S2");

                    myRef.setValue("1");
                }else {
                    Toast.makeText(getBaseContext(),"OFF",Toast.LENGTH_SHORT).show();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("S2");

                    myRef.setValue("0");
                }
            }


        });
        cSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true){
                    Toast.makeText(getBaseContext(),"ON",Toast.LENGTH_SHORT).show();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("S3");

                    myRef.setValue("1");
                }else {
                    Toast.makeText(getBaseContext(),"OFF",Toast.LENGTH_SHORT).show();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("S3");

                    myRef.setValue("0");
                }
            }


        });
        dSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true){
                    Toast.makeText(getBaseContext(),"ON",Toast.LENGTH_SHORT).show();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("S4");

                    myRef.setValue("1");
                }else {
                    Toast.makeText(getBaseContext(),"OFF",Toast.LENGTH_SHORT).show();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("S4");

                    myRef.setValue("0");
                }
            }


        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK  &&  null != data) {
                    ArrayList result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView.setText((CharSequence) result.get(0));


                    if(result.contains("turn on all lights  ") || result.contains("all lights on")
                            || result.contains(" all on") || result.contains("switch on all lights"))
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("S1");
                        DatabaseReference myRef1 = database.getReference("S2");
                        DatabaseReference myRef2= database.getReference("S3");
                        DatabaseReference myRef3 = database.getReference("S4");

                        myRef.setValue("1");
                        myRef1.setValue("1");
                        myRef2.setValue("1");
                        myRef3.setValue("1");

                    }
                    else  if(result.contains("turn off all lights  ") || result.contains("all lights off")
                            || result.contains(" all off") || result.contains("switch off all lights"))
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("S1");
                        DatabaseReference myRef1 = database.getReference("S2");
                        DatabaseReference myRef2= database.getReference("S3");
                        DatabaseReference myRef3 = database.getReference("S4");

                        myRef.setValue("0");
                        myRef1.setValue("0");
                        myRef2.setValue("0");
                        myRef3.setValue("0");

                    }



                    else if(result.contains("turn on light 1 ") || result.contains("turn on light one")
                            || result.contains(" light  1 on") || result.contains("light one on")
                            || result.contains("1 light on")   || result.contains("one light on"))
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("S1");

                        myRef.setValue("1");

                    }
                    else if(result.contains("turn on light 2 ") || result.contains("turn on light two")
                            || result.contains(" light  2 on") || result.contains("light who on")
                            || result.contains("2 light on")   || result.contains("two light on"))
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("S2");

                        myRef.setValue("1");

                    }
                    else if(result.contains("turn on light 3 ") || result.contains("turn on light three")
                            || result.contains(" light  3 on") || result.contains("light three on")
                            || result.contains("3 light on")   || result.contains("three light on"))
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("S3");

                        myRef.setValue("1");

                    }
                    else if(result.contains("turn on light 4 ") || result.contains("turn on light four")
                            || result.contains(" light  4 on") || result.contains("light four on")
                            || result.contains("4 light on")   || result.contains("four light on"))
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("S3");

                        myRef.setValue("1");

                    }




                    else if(result.contains("turn off light 1 ") || result.contains("turn off light one")
                            || result.contains(" light  1 off") || result.contains("light one off")
                            || result.contains("1 light off")   || result.contains("one light off"))
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("S1");

                        myRef.setValue("0");

                    }
                    else if(result.contains("turn off light 2 ") || result.contains("turn off light two")
                            || result.contains(" light  2 off") || result.contains("light two off")
                            || result.contains("2 light off")   || result.contains("tho light off"))
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("S2");

                        myRef.setValue("0");

                    }
                    else if(result.contains("turn off light 3 ") || result.contains("turn off light three")
                            || result.contains(" light  3 off") || result.contains("light three off")
                            || result.contains("3 light off")   || result.contains("three light off"))
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("S3");

                        myRef.setValue("0");

                    }
                    else if(result.contains("turn off light 4 ") || result.contains("turn off light four")
                            || result.contains(" light  4 off") || result.contains("light four off")
                            || result.contains("4 light off")   || result.contains("four light off"))
                    {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("S4");

                        myRef.setValue("0");

                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"enter again",Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }
}
