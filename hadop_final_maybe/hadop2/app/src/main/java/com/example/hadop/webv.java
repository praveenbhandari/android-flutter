package com.example.hadop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class webv extends AppCompatActivity {
    WebView web;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webv);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        web=findViewById(R.id.web  );
        b=getIntent().getExtras();
        String d= b.getString("disease");
        Log.e("datttttt",""+d);
        String uu="https://www.mayoclinic.org/search/search-results?q="+d;
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setLoadWithOverviewMode(true);

//        final WebSettings settings = web.getSettings();
//        settings.setJavaScriptEnabled(true);
//        web.setWebViewClient(new WebViewClient());

//        web.loadUrl("https://google.com");


//        String url = ;
        try {

            Log.e("hmmmm", "in    tryyyy" );
            Document document = Jsoup.connect(uu).get();

//            Log.e("hmmmm", "" + document.getElementsByClass("noimg"));
//            Log.e("hmmmm", "" + document.getElementsByClass("noimg").first());
//            Log.e("hmmmm", "" + document.getElementsByClass("noimg").first().attr("h3"));
//            Log.e("hmmmm", "" + document.getElementsByClass("noimg").first().hasAttr("href"));
            Element a=document.getElementsByClass("noimg").select("a").first();
            uu=a.attr("href");
            Log.e("hmmmm", "" + a.attr("href"));

//            Log.e("hmmmm", "" + document.getElementsByClass("noimg").attr("href"));
//            Log.e("hmmmm iddddd", "" + document.getElementById("b7b1c41a35e845c7afa0570b78c9ea93"));
        }
        catch (Exception e){
            Log.e("EXCEPTION",""+e);
        }
        web.loadUrl(uu);
//        web.find
    }

}