package com.example.a10services.weather;

import android.util.Log;
import android.util.Pair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class WeatherFromTheWeb {
    private static final String WEATHER_API = "https://api.openweathermap.org/data/2.5/weather";
    private static final String WEATHER_API_KEY = "6565d5a438410f02d391a6dee21a7eed";

    public static String getWeatherForCity(String city) {
        try {
            URL url = new URL(WEATHER_API + "?appid=" + WEATHER_API_KEY + "&q=" + city);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);

            InputStream is = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();
            while(line != null) {
                sb.append(line + "\n");
                line = reader.readLine();
            }
            is.close();
            con.disconnect();

            return sb.toString();
        }catch (Exception ex) {
            Log.e("GETWEATHER", ex.toString());
        }

        return "";
    }
}
