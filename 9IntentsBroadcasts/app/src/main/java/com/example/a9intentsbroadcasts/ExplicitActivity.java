package com.example.a9intentsbroadcasts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ExplicitActivity extends AppCompatActivity {
    public static final String EXTRA_CONTENT = "EXTRAEXTRA";
    private TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        txtStatus = findViewById(R.id.txtStatus);
        txtStatus.setText(getIntent().getStringExtra(EXTRA_CONTENT));
    }
}