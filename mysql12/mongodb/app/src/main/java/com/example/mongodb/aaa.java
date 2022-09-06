package com.example.mongodb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class aaa extends AppCompatActivity {
    connection connection;

    String nm,em,password;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aaa);
        tv=findViewById(R.id.name11);

        connection = new connection();

        String z="";


        try {
            Connection con = connection.CONN();
            if (con == null) {
                z = "Please check your internet connection";
            } else {

                String query="select * from user";

                Statement stmt = con.createStatement();
               // stmt.executeUpdate(query);
                ResultSet rs=stmt.executeQuery(query);

                while (rs.next())

                {
                    nm= rs.getString(1);
                    em=rs.getString(2);
                    password=rs.getString(3);



                    tv.setText(" ' name= ' "+nm+
                            " ' email= ' "+em+
                            " ' password= ' "+password);

                }


                //z = "Register successfull";


            }
        }
        catch (Exception ex)
        {
            z = "Exceptions"+ex;
        }
    }
}
