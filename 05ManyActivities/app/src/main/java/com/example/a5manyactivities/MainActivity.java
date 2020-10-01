package com.example.a5manyactivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CAT = 1;
    public static final int REQUEST_DOG = 2;

    EditText txtMeowMix;
    EditText txtPuppyChown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMeowMix = findViewById(R.id.txtMeowMix);
        txtPuppyChown = findViewById(R.id.txtPuppyChow);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            toast("There was no result");
            return;
        }

        if(data == null) {
            toast("No result data");
            return;
        }

        switch(requestCode){
            case REQUEST_CAT:
                toast(data.getStringExtra(CatlandActivity.EXTRA_MESSAGE));
                break;
            case REQUEST_DOG:
                toast(data.getStringExtra(DogTownActivity.EXTRA_DOG));
                break;
        }

    }

    void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void sendToCatlandClicked(View v){
        /*
        Intent -> Activity/Service/Something Else(Share,open with, etc.)
            Extras(Bundle)
            Intent(Bundle) -> Either make a bundle or add through the Intent
        */

        Intent intentWithTheCatbassador = new Intent(this, CatlandActivity.class);
        intentWithTheCatbassador.putExtra(CatlandActivity.EXTRA_MESSAGE, txtMeowMix.getText().toString());
        // startActivity(intentWithTheCatbassador);
        startActivityForResult(intentWithTheCatbassador, REQUEST_CAT);
    }

    public void sendToDogTownClicked(View v){
        Intent intentWithTheDogmayor = new Intent(this, DogTownActivity.class);
        intentWithTheDogmayor.putExtra(DogTownActivity.EXTRA_DOG, txtPuppyChown.getText().toString());
        startActivityForResult(intentWithTheDogmayor, REQUEST_DOG);
    }
}