package com.example.a9intentsbroadcasts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ImplicitActivity extends AppCompatActivity {
    private TextView txtImplicitText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        txtImplicitText = findViewById(R.id.txtImplicitText);

        txtImplicitText.setText(getIntent().getStringExtra(Intent.EXTRA_TEXT));
    }
}