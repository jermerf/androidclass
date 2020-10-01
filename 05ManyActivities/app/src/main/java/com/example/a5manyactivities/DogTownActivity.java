package com.example.a5manyactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DogTownActivity extends AppCompatActivity {
    public static final String EXTRA_DOG = "bark scow cat";

    TextView lblDogmayor;
    EditText txtResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_town);
        lblDogmayor = findViewById(R.id.lblDogmayor);
        txtResponse = findViewById(R.id.txtResponse);

        lblDogmayor.setText(getIntent().getStringExtra(EXTRA_DOG));
    }

    public void dogmayorSaysClicked(View v) {
        Intent result = new Intent();
        result.putExtra(EXTRA_DOG, txtResponse.getText().toString());
        setResult(RESULT_OK, result);
        finish();
    }
}