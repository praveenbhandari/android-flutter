package com.example.andall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    Button b;
 public static TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       b=findViewById(R.id.button2);
       tv=findViewById(R.id.textView);
      
       b.setOnClickListener(new View.OnClickListener(){
               public void onClick(View v ){
                   fetchData fd=new fetchData();
                   fd.execute();

        }
       });

    }
}