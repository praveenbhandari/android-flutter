package com.example.buttonall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Intent i;
    String[] arr = {"Notification", "Biometric", "Current location", "Geofence", "f", "g"};

    //Array a=();
//ArrayList a=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ArrayAdapter adapter1=new ArrayAdapter<String>(this,R.layout.listacti)


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listactivity, arr);
        ListView lv = findViewById(R.id.listview);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int ss = position + 1;
                Toast.makeText(MainActivity.this, ss + "", Toast.LENGTH_SHORT).show();

                switch (ss) {
                    case 1:
                        i = new Intent(MainActivity.this, notification.class);
                        startActivity(i);
                        break;
                    case 2:
                        Intent ii = new Intent(MainActivity.this, biometric.class);
                        startActivity(ii);
                        break;
                    case 3:
                        Intent as = new Intent(MainActivity.this, MapsActivity.class);
                        startActivity(as);
                        break;
                    case 4:
                        Intent geo = new Intent(MainActivity.this, geofence.class);
                        startActivity(geo);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "select input", Toast.LENGTH_SHORT).show();
                }


            }
        });


//        b1 = findViewById(R.id.but1);
        //  SearchView search = findViewById(R.id.search);
        //search.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
    }
   /* public boolean onQueryTextSubmit(String query){
        return false;
    }
    public boolean onQueryTextChange (String newText){
        String text=newText;
        adapter.filter(text);
        return false;
    }
  */
}



/*


<Button
                android:id="@+id/but1"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="1" />

<Button
                android:id="@+id/but2"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="2" />

<Button
                android:id="@+id/but3"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="2" />

<Button
                android:id="@+id/but4"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="2" />

<Button
                android:id="@+id/but5"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="2" />

<Button
                android:id="@+id/but6"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="2" />

<Button
                android:id="@+id/but7"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="2" />

<Button
                android:id="@+id/but8"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="8" />

<Button
                android:id="@+id/but9"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="9" />

<Button
                android:id="@+id/but10"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="10" />

<Button
                android:id="@+id/but11"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="11" />

<Button
                android:id="@+id/but12"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="12" />


<Button
                android:id="@+id/but13"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="13" />

<Button
                android:id="@+id/but14"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="14" />

<Button
                android:id="@+id/but15"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="15" />

<Button
                android:id="@+id/but16"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="16" />

<Button
                android:id="@+id/but17"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="17" />

<Button
                android:id="@+id/but18"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="18" />

<Button
                android:id="@+id/but19"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="19" />

<Button
                android:id="@+id/but20"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="20" />

<Button
                android:id="@+id/but21"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="21" />

<Button
                android:id="@+id/but22"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="22" />

<Button
                android:id="@+id/but23"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="23" />

<Button
                android:id="@+id/but24"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="1=24" />

<Button
                android:id="@+id/but25"
                        android:layout_width="match_parent"
                        android:layout_height="50dp"
                        android:text="25" />
*/