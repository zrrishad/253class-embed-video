 package com.example.a253classvideoembed;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    WebView edWeb;
public static String video_url="url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        edWeb=findViewById(R.id.edWeb);


        edWeb.getSettings().setJavaScriptEnabled(true);
        edWeb.loadUrl(video_url);

    }
}