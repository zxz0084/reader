package com.swufestu.reader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.swufestu.reader.pramater.Constrant;

import java.io.File;

public class HtmlBrowser extends Activity {
    private String Filep=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_browser);
        Intent intent=getIntent();
        if(intent==null){
            finish();
            return;
        }
        Bundle bundle=intent.getExtras();
        if(bundle==null){
            finish();
            return;
        }
        Filep=bundle.getString("filePath");
        if(Filep==null||Filep.equals("")){
            finish();
            return;
        }
        WebView wv=findViewById(R.id.webview);
        ReadFileRandom readFileRandom = new ReadFileRandom(Filep);
        File f=new File(Filep);
    }
}