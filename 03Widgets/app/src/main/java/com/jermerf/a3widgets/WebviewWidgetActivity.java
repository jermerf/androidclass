package com.jermerf.a3widgets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class WebviewWidgetActivity extends AppCompatActivity {
    WebView browser;
    EditText txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        browser = findViewById(R.id.webviewBrowser);
        txtAddress = findViewById(R.id.txtAddress);

        browser.setWebViewClient(new WebViewClient());
        browser.getSettings().setJavaScriptEnabled(true);
    }

    public void goToUrl(View v) {
        browser.loadUrl(txtAddress.getText().toString());
    }
}