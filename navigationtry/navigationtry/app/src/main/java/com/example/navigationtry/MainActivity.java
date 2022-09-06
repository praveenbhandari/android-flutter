package com.example.navigationtry;



import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.navigationtry.R;
import com.example.navigationtry.frag_test;
import com.example.navigationtry.frag_test1;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView nav;
    Button b,b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=findViewById(R.id.button);
        b1=findViewById(R.id.button1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer);
        nav = findViewById(R.id.navigation_view);
        nav.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag,new frag_test()).commit();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frag, new frag_test1()).commit();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frag, new frag_test()).commit();
            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.e("itemmmm",""+item.getTitle());
        Log.e("itemmmm",""+item.getItemId());
        Toast.makeText(this,""+item.getTitle(),Toast.LENGTH_SHORT).show();
        Log.e("trying","to change");
        if(item.getTitle() == "somwthing 1") {
            getSupportFragmentManager().beginTransaction().replace(R.id.frag, new frag_test1()).commit();
        }else if(item.getTitle() == "somwthing 2"){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag, new frag_test()).commit();
        }
        Log.e("done","to change");

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
//
