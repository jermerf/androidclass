package com.example.a9intentsbroadcasts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtContent = findViewById(R.id.txtContent);

        SensorManager senMan = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = senMan.getDefaultSensor(SensorManager.SENSOR_LIGHT);
        senMan.registerListener(lightListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    SensorEventListener lightListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            txtContent.setText("Light: " + event.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) { }
    };

    public void explicitIntentClicked(View v) {
        Intent explicitIntent = new Intent(this, ExplicitActivity.class);
        startActivity(explicitIntent);
    }


    public void explicitIntentWithExtrasClicked(View v) {
        Intent explicitIntent = new Intent(this, ExplicitActivity.class);
        explicitIntent.putExtra(ExplicitActivity.EXTRA_CONTENT, txtContent.getText().toString());
        startActivity(explicitIntent);
    }

    public void implicitIntentClicked(View v) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, txtContent.getText().toString());
        startActivity(Intent.createChooser(shareIntent, "Edison, take the wheel"));
    }


}