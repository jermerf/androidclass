package com.example.a10services.weather;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class WeatherService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new WeatherBinder();
    }

    private static class WeatherBinder extends Binder implements IWeather{
        private String response;

        @Override
        public String getWeatherResponse() {
            return this.response;
        }

        @Override
        public void getWeather(String city, Runnable onResponse) {
            new Thread(() -> {
                this.response = WeatherFromTheWeb.getWeatherForCity(city);
                new Thread(onResponse).start();
            }).start();
        }

    }

}
