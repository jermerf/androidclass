package com.jermerf.a3widgets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ButtonWidgetsActivity extends AppCompatActivity {
    Button btnFirst;
    Switch switchToggleWifi;
    ImageButton btnWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        btnFirst = findViewById(R.id.btnFirst);
        switchToggleWifi = findViewById(R.id.switchToggleWifi);
        btnWifi = findViewById(R.id.btnWifi);

        btnFirst.setOnClickListener(imgButtonListener);
        switchToggleWifi.setOnClickListener(switchToggleListener);
        btnWifi.setOnClickListener(imgButtonListener);

        btnWifi.setEnabled(switchToggleWifi.isChecked());
    }

    private View.OnClickListener imgButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(ButtonWidgetsActivity.this, "To our health!", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener switchToggleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnWifi.setEnabled(switchToggleWifi.isChecked());
        }
    };
}