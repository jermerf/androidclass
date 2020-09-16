package com.jermerf.a3widgets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void textWidgets(View v) {
        startActivity(new Intent(this, TextWidgetsActivity.class));
    }

    public void buttonWidgets(View v) {
        startActivity(new Intent(this, ButtonWidgetsActivity.class));
    }

    public void webviewWidget(View v) {
        startActivity(new Intent(this, WebviewWidgetActivity.class));
    }
}