package com.example.a13_webviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ChromeClientActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrome_client);


        mWebView = findViewById(R.id.webview_chrome_client);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

                Toast.makeText(ChromeClientActivity.this, "js alert", Toast.LENGTH_SHORT).show();
                result.confirm();
                return true;
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Toast.makeText(ChromeClientActivity.this,consoleMessage.message(),Toast.LENGTH_SHORT).show();
                return  true;
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.e("progress", "progress: " + newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });

        mWebView.loadUrl("file:///android_asset/chrome_client_web.html");
    }
}
