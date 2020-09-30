package com.example.a10services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a10services.weather.IWeather;
import com.example.a10services.weather.WeatherService;

public class MainActivity extends AppCompatActivity implements ServiceConnection {
    private EditText txtCity;
    private TextView txtResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCity = findViewById(R.id.txtCity);
        txtResponse = findViewById(R.id.txtResponse);
    }

    public void startToasterService(View v) {
        Intent toastIntent = new Intent(this, ToasterService.class);
        startService(toastIntent);
    }

    public void stopToasterService(View v) {
        Intent toastIntent = new Intent(this, ToasterService.class);
        stopService(toastIntent);
    }

    private IWeather weatherBinding;

    public void startWeatherService(View v) {
        Intent weatherIntent = new Intent(this, WeatherService.class);
        getApplicationContext().bindService(weatherIntent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        weatherBinding = (IWeather) service;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    public void stopWeatherService(View v) {
        getApplicationContext().unbindService(this);
    }

    public void getWeatherClicked(View v){
        if (weatherBinding == null) {
            Toast.makeText(this, "Service not bound", Toast.LENGTH_SHORT).show();
            return;
        }

        weatherBinding.getWeather(txtCity.getText().toString(), () -> {
            runOnUiThread(() -> {
                txtResponse.setText(weatherBinding.getWeatherResponse());
            });

        });


    }

}