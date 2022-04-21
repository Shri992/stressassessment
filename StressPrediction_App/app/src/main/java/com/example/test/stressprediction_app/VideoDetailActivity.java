package com.example.test.stressprediction_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class VideoDetailActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        webView = findViewById(R.id.webView);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String link = intent.getStringExtra("link");

        String video_link = "https://www.youtube.com/embed/"+link;



        String videoStr = "<html><body><br><iframe width=\"420\" height=\"500\" src=\""+video_link+"\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

        //Toast.makeText(getApplicationContext(),videoStr,Toast.LENGTH_LONG).show();

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        webView.loadData(videoStr, "text/html", "utf-8");

    }
}
