package com.example.navi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        navigationView = findViewById(R.id.navigationview);
//        drawerLayout = findViewById(R.id.drawer);
//
//        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.a:
//                        Toast.makeText(MainActivity.this, "1", Toast.LENGTH_LONG).show();
//                        break;
//                    case R.id.b:
//                        Toast.makeText(MainActivity.this, "2", Toast.LENGTH_LONG).show();
//                        break;
//                    case R.id.c:
//                        Toast.makeText(MainActivity.this, "3", Toast.LENGTH_LONG).show();
//                        break;
//                    case R.id.d:
//                        Toast.makeText(MainActivity.this, "4", Toast.LENGTH_LONG).show();
//                        break;
//                    case R.id.e:
//                        Toast.makeText(MainActivity.this, "5", Toast.LENGTH_LONG).show();
//                        break;
//                    default:
//                        Toast.makeText(MainActivity.this, "Null", Toast.LENGTH_LONG).show();
//                }
//                return true;
//            }
//        });


    }
}