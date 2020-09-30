package com.example.a10services.weather;

public interface IWeather {
    void getWeather(String city, Runnable onResponse);
    String getWeatherResponse();
}
