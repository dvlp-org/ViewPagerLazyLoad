package com.example.lvzishen.viewpagerlazyload;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by liubaba on 2017/9/11.
 */

public class H5ToAppActivity extends Activity {
    private String url;
    private WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5_app);


        webview = (WebView) findViewById(R.id.webviewh5);
        url = "file:///android_asset/index.html";


        WebSettings wSet = webview.getSettings();
        wSet.setJavaScriptEnabled(true);
        webview.loadUrl(url);
    }
    }
