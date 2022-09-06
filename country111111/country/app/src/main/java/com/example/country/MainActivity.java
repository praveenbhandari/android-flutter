package com.example.country;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Room;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.country.db.Country;
import com.example.country.db.country_db;
import com.example.country.db.doaaa;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.AsynchronousByteChannel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    StringBuilder ress;
    CustomList customList;
    connectivity c_customList;
    ListView listView;
    List<Country> csc;
    View parentLayout;
//    ImageView ii;
    ConnectivityManager connectivityManager;
//    country_db db;
//    doaaa userDao;
    ArrayList<Custom_list_data> arrayList = new ArrayList<>();
int chec=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
listView=findViewById(R.id.list_view);
        Log.e("onCreate","starting");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        parentLayout = findViewById(android.R.id.content);
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
check_Cconnection();
//ii=findViewById(R.id.flag);
//        new downloa().execute("https://purepng.com/public/uploads/large/purepng.com-lionanimalslion-981524674150xt3tu.png");
//ii.setImageResource(R.drawable.syr);



//        if(chec==0){
//
//        }
//        else{
//
//        }

//        try {
//            AsyncTask.execute(() -> runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Log.e("starting databse", "databsss");
//                    db = Room.databaseBuilder(getApplicationContext(),
//                            country_db.class, "country").build();
//
//                    try {
//                        Log.e("starting doaaa", "DOA");
//                        userDao = db.ddoa();
//                        List<Country> users = userDao.getAll();
//                        Log.e("getting doaaa", "DOA::" + users);
//                    } catch (Exception e) {
//                        Log.e("error", "" + e);
//                    }
//                }
//            }) );
//
//
//        }
//        catch (Exception e){
//            Log.e("error ","database");
//        }

    }
    void check_Cconnection() {
        if (connectivityManager.getActiveNetworkInfo() == null) {
            Log.e("in", "connection check");
            Snackbar.make(parentLayout, "NO INTERNET CONNECTION", Snackbar.LENGTH_INDEFINITE).setBackgroundTint(Color.RED).setAction("OK", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    check_Cconnection();
                }
            }).setActionTextColor(Color.YELLOW).show();
            Log.e("offlineee","offline....");
            try{chec=1; gett();}catch(Exception e){chec=0; Log.e("error","offlineeeee......"+e);}
//            email.setEnabled(false);
//            pass.setEnabled(false);
//            login.setEnabled(false);
//            signup.setEnabled(false);
        } else {
            Log.e("online","nettttt....");
            try{chec=1; get_datt();}catch(Exception e){chec=0; Log.e("error","nettttt...."+e);}
//            email.setEnabled(true);
//            pass.setEnabled(true);
//            login.setEnabled(true);
//            signup.setEnabled(true);
        }
    }

    void get_datt() {
        try {
            Log.e("in getdat","getttting");
            URL u = new URL("https://restcountries.eu/rest/v2/region/asia");
            URLConnection cc = u.openConnection();
            BufferedReader bb = new BufferedReader(new InputStreamReader(cc.getInputStream()));

            String a;
            ress=new StringBuilder();

//            int i;
            while ((a = bb.readLine()) != null) {

                Log.e("itemreadln......","........................."+a.length());
//                Log.e("itemreadln",""+a);
                ress.append(a);




//                JSONObject jj=new JSONObject(a);
//                Log.e("json data",""+jj.toString());


                }
            bb.close();

            //            JSONArray aa=jj.getJSONArray();
//runOnUiThread(new Runnable() {
//    @Override
//    public void run() {
//        try{
//            Log.e("getting","dataaaa");
////            Thread.sleep(5000);
////            gett();
//
//        }
//        catch (Exception e){
//            Log.e("error",""+e);
//        }
//    }
//});

        }
        catch(Exception e){
            Log.e("error.....",""+e);
        }
        Log.e("jsooo",""+ress.length());
try{
    JSONArray ja=new JSONArray(ress.toString());
    Log.e("JSON OBJ len",""+ja.length());
//    Log.e("JSON OBJ",""+ja.toString());
    int count=0;

    arrayList.clear();
    for(int i=0;i< ja.length();i++){
       count++;
        JSONObject jb=ja.getJSONObject(i);
        String name=jb.get("name").toString();
        String capti=jb.get("capital").toString();
        String regi=jb.get("region").toString();
        String subreg=jb.get("subregion").toString();
        String flag=jb.get("flag").toString();
        String borders=jb.get("borders").toString();
        String languages=jb.get("languages").toString();
        String population=jb.get("population").toString();
//        Log.e("item-"+i,"name-"+name+"/n capital-"+capti+"/nregion-"+regi+"/nsubregion-"+subreg+"/nflag-"+flag+"/nborder-"+borders+"/nlanguages-"+languages+"/npopulation-"+population);
//new DownloadImage().execute(flag);
//       Country ccc=new Country("1","sasdasd","capti","refgii","subb","flagg","borders","langiage","populati");
//        userDao.insert_Data(ccc);
//        Country c=new Country(String.valueOf(String.valueOf(i + 1)),name,capti,regi,subreg,borders,languages,population,flag);
//
//        userDao.insert_Data(c);

        int finalI = i;
        AsyncTask.execute(() -> runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    save_dat(String.valueOf(finalI + 1),name,capti,regi,subreg,borders,languages,population,flag);
                    new downloa().execute(flag,name);
                    Log.e("doneee","doneeee"+flag);
                }
                catch(Exception e){
                    Log.e("ERORRR","..."+e);
                }

            }
        }));



        arrayList.add(new Custom_list_data(String.valueOf(i + 1), name, capti, regi,subreg,flag,borders,languages,population));
    }
    customList = new CustomList(MainActivity.this, arrayList);
    listView.setAdapter(customList);
//    Object jj=new JSONObject(ress.toString()).get("Value");
//    Log.e("all data",""+userDao.getAll().size());
//    Log.e("all data",""+userDao.getAll().indexOf(1));

}
catch (JSONException e){
    Log.e("error","eee....."+e);
}

    }

int ccasc=0;
    void save_dat(String srn,String name,String capti, String regi, String subreg,String flag, String borders,String languages, String population){
    country_db db=country_db.getdbInstance(this.getApplicationContext());

//    Country country=new Country(ccasc++,name,capti,regi,subreg,flag,borders,languages,population);
        Country country=new Country();
    country.c_srnum = Integer.parseInt(srn);
    country.c_name = name;
    country.c_capti = capti;
    country.c_regi = regi;
    country.c_subreg = subreg;
    country.c_borders = borders;
    country.c_languages = languages;
    country.c_flag = flag;
    country.c_population = population;
    Log.e("inserting data","indatabase");
    db.ddoa().insertCountry(country);
        Log.e("done inserting data","indatabase,...............");
//finish();
//        gett();

    }
    void gett(){

        arrayList.clear();


        country_db db=country_db.getdbInstance(this.getApplicationContext());
       csc= db.ddoa().getAll();
//       Log.e("dataaa",""+csc.getValue().size());

       Log.e("dataaa......","sixee"+csc.size());
       for(int ij=0;ij<csc.size();ij++){
           Log.e("dataaa......","nameeee-"+csc.get(ij).c_name);
           arrayList.add(new Custom_list_data(String.valueOf(csc.get(ij).c_srnum), csc.get(ij).c_name, csc.get(ij).c_capti, csc.get(ij).c_regi,csc.get(ij).c_subreg,csc.get(ij).c_flag,csc.get(ij).c_borders,csc.get(ij).c_languages,csc.get(ij).c_population));


       }
        customList = new CustomList(MainActivity.this, arrayList);
        listView.setAdapter(customList);


    }

    public class downloa extends AsyncTask<String,Void, Bitmap>{
        private Bitmap downloadImageBitmap(String sUrl,String f_name) {
            Log.e("downloadinggg......"+f_name,"............."+sUrl);
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(sUrl).openStream();   // Download Image from URL
                bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
                saveImage(getApplicationContext(), bitmap, f_name+".png");
                inputStream.close();

            } catch (Exception e) {
                Log.e("DownloadImage", ""+e);
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            return downloadImageBitmap(strings[0],strings[1]);
        }
        @Override
        protected void onPostExecute(Bitmap result) {
//
//            if (result != null) {
//                File dir = new File(Context.getFilesDir(), "MyImages");
//                if(!dir.exists()){
//                    dir.mkdir();
//                }
//                File destination = new File(dir, "image.jpg");
//
//                try {
////                    destination.createNewFile();
//                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                    result.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
//                    byte[] bitmapdata = bos.toByteArray();
//
//                    FileOutputStream fos = new FileOutputStream(destination);
//                    fos.write(bitmapdata);
//                    fos.flush();
//                    fos.close();
//                    selectedFile = destination;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
        public void saveImage(Context context, Bitmap b, String imageName) {
            FileOutputStream foStream;
            try {
                File file= getApplicationContext().getFileStreamPath(imageName);
//                if (file.exists()) {
//                    Log.e("file", " exists!");
//                }else {
//?
                    foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
//                    b.compress(Bitmap.CompressFormat.PNG, 100, foStream);
                   Bitmap bb= Bitmap.createBitmap(300,300);
                   bb.compress(Bitmap.CompressFormat.PNG,100,foStream);
                    foStream.close();
                    Log.e("downloadinggg......","doneeeeee.............");
//                }
            } catch (Exception e) {
                Log.e("saveImage", "Exception 2, Something went wrong!"+e);
                e.printStackTrace();
            }
        }
    }
    public Bitmap loadImageBitmap(Context context, String imageName) {
        Bitmap bitmap = null;
        FileInputStream fiStream;
        try {
            fiStream    = context.openFileInput(imageName);
            bitmap      = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 3, Something went wrong!");
            e.printStackTrace();
        }
        return bitmap;
    }
}

