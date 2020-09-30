package com.example.a12sensors;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String NOTIF_GROUP_CAT = "meowmeow";
    private static final String NOTIF_TESLA_CHANNEL = "tesla";
    private static final String NOTIF_EDISON_CHANNEL = "edison";
    private NotificationManager notifMan;
    private SensorManager sensorMan;
    private Sensor accelerometer;
    private Sensor light;

    private EditText txtNotification;
    private TextView txtX;
    private TextView txtY;
    private TextView txtZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNotification = findViewById(R.id.txtNotification);
        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        txtZ = findViewById(R.id.txtZ);

        notifMan = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        sensorMan = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        light = sensorMan.getDefaultSensor(Sensor.TYPE_LIGHT);

//        sensorMan.registerListener(accelListener,accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorMan.registerListener(accelListener,light, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private SensorEventListener accelListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor == accelerometer && event.values.length>=3) {
                txtX.setText("X: " + event.values[0]);
                txtY.setText("Y: " + event.values[1]);
                txtZ.setText("Z: " + event.values[2]);
            }

            if(event.sensor == light && event.values.length>=1) {
                txtX.setText("Light: " + event.values[0]);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };



    int notifID = 0;
    public void notifyClicked(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannelGroup catGroup = new NotificationChannelGroup(NOTIF_GROUP_CAT, "Cats");
            NotificationChannel teslaChannel = new NotificationChannel(NOTIF_TESLA_CHANNEL, "Tesla", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel edisonChannel = new NotificationChannel(NOTIF_EDISON_CHANNEL, "Edison", NotificationManager.IMPORTANCE_DEFAULT);
            notifMan.createNotificationChannelGroup(catGroup);
            notifMan.createNotificationChannel(teslaChannel);
            notifMan.createNotificationChannel(edisonChannel);

            Notification teslaNotification = getNotification(NOTIF_TESLA_CHANNEL);
            notifMan.notify(notifID++, teslaNotification);
            Notification edisonNotification = getNotification(NOTIF_EDISON_CHANNEL);
            notifMan.notify(notifID++, edisonNotification);
        } else {
            Notification notification = getNotification(NotificationChannel.DEFAULT_CHANNEL_ID);
            notifMan.notify(notifID++, notification);
        }
    }

    private Notification getNotification(String channel){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channel);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(channel)
                .setContentText(txtNotification.getText());

        return builder.build();
    }
}