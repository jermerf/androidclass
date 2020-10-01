package com.example.a5manyactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CatlandActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "purrpurrpurr";
    TextView lblCatbassador;
    EditText txtResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catland);

        lblCatbassador = findViewById(R.id.lblDogmayor);
        txtResponse = findViewById(R.id.txtResponse);

        String message = getIntent().getStringExtra(EXTRA_MESSAGE);
        lblCatbassador.setText(message);
    }

    public void sendResponseClicked(View v) {
        Intent result = new Intent();
        result.putExtra(EXTRA_MESSAGE, txtResponse.getText().toString());
        setResult(RESULT_OK, result);
        finish();
    }
}