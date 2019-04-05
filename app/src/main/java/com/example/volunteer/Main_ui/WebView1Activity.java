package com.example.volunteer.Main_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.volunteer.R;

public class WebView1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view1);
        WebView webView=findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://appapi.zyh365.com/static/html/goodActivity.html?id=597ebe7c-3080-11e6-b103-7cd30ab49c54");

    }
}
