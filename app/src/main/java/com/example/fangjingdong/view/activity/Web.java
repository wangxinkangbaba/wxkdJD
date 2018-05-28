package com.example.fangjingdong.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.fangjingdong.R;

public class Web extends AppCompatActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        web = (WebView) findViewById(R.id.web);
        Intent intent = getIntent();
        String detailUrl = intent.getStringExtra("detailUrl");
        if (detailUrl != null) {
            web.loadUrl(detailUrl);

            //webview一系列设置
            web.setWebViewClient(new WebViewClient());//在当前应用打开,而不是去浏览器
            WebSettings settings = web.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
        }
    }
}
