package com.example.mongodb_realm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.log.LogLevel;
import io.realm.log.RealmLog;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class MainActivity extends AppCompatActivity {
    EditText email,pass;
    Button login, create,anonymous;
    User userd;
App tasker;
String opt="";
    String appId = "mongotest-aqvpn";  // Replace with proper Application ID
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.input_username);
        pass=findViewById(R.id.input_password);
        login=findViewById(R.id.button_login);
        create=findViewById(R.id.button_create);
        anonymous=findViewById(R.id.anonymous);

        Realm.init(this);
        tasker = new App(new AppConfiguration.Builder(appId).build());
//        // Enable more logging in debug mode
//        if (BuildConfig.DEBUG) {
//            RealmLog.setLevel(LogLevel.ALL);
//        }
//        Log.v("MONGO", "Initialized the Realm App configuration for: ${taskApp.configuration.appId}");

        anonymous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Credentials anonymousCredentials = Credentials.anonymous();

                tasker.loginAsync(anonymousCredentials, new App.Callback<User>() {
                    @Override
                    public void onResult(App.Result<User> it) {
                        if (it.isSuccess()) {
                            userd = tasker.currentUser();
                            opt="Successfully authenticated anonymously as \n "+userd;
                            Log.e("TAG", opt);
                            inte(opt);
                        } else {
                            Toast.makeText(MainActivity.this,"ERROR LOGGIN IN",Toast.LENGTH_SHORT).show();
                            Log.e("TAG", it.getError().toString());
                        }
                    }
                });
            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email==null  || pass==null){
                    Toast.makeText(MainActivity.this,"ENTER ALL DETAILS",Toast.LENGTH_SHORT).show();
                }else{
                String user=email.getText().toString();
                String password=pass.getText().toString();

                tasker.getEmailPasswordAuth().registerUserAsync(user,password, new App.Callback<Void>() {
                    @Override
                    public void onResult(App.Result<Void> it) {
                        if(it.isSuccess()){
                            userd = tasker.currentUser();
                            opt="Successfully authenticated with email as \n"+userd;
                            Log.e("TAG",opt );
                            inte(opt);
                        }
                        else{

                            Toast.makeText(MainActivity.this,"ERROR LOGGIN IN",Toast.LENGTH_SHORT).show();
                            Log.e("TAG", it.getError().toString());
                        }
                    }
                });}

            }
        });
login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});


    }
    void inte(String data){
        Intent i = new Intent(this,userdata.class);
        i.putExtra("data",data);
        startActivity(i);
    }

}