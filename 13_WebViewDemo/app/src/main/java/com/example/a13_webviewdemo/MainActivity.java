package com.example.a13_webviewdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.btn);

        mWebView = findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/growingTouchPopupWindow.html");

        mWebView.addJavascriptInterface(new JSBridge(), "android");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.loadUrl("javascript:sum(3, 19)");
                testEvaluateJavascript();
            }
        });
    }

    private void  testEvaluateJavascript() {
        mWebView.evaluateJavascript("getGreetings()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                Log.i("info", "evaluateJavascript result = " + s);
            }
        });
    }

    public class JSBridge {
        @JavascriptInterface
        public void toastMessage(String message) {
            Toast.makeText(getApplicationContext(), "toast :" + message, Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface
        public void onSumResult(int result) {
            Log.i("info", "sum result = " + result);
        }
    }
}
