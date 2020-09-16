package com.jermerf.a2resourcesandbuttons;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button buttonThatMovesTheText;
    EditText editTextSource;
    TextView labelForStatus;

    EditText txtToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonThatMovesTheText = findViewById(R.id.btnMoveText);
        editTextSource = findViewById(R.id.txtSource);
        labelForStatus = findViewById(R.id.lblStatus);

        txtToast = findViewById(R.id.txtToast);
    }

    public void moveTextButtonClicked(View v){
        // Move text from edittext to button
        buttonThatMovesTheText.setText(editTextSource.getText().toString());
        labelForStatus.setText(R.string.status_changed);
    }

    public void toastMeSomeToast(View v){
        Toast.makeText(this, txtToast.getText(), Toast.LENGTH_LONG).show();
    }

    public void goToSecondActivity(View v) {
        Intent goToSecondActivity = new Intent(this, SecondActivity.class);
        startActivity(goToSecondActivity);
    }

}