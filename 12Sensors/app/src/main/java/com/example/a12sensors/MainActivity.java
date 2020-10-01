package com.example.a12sensors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String NOTIF_GROUP_CAT = "meowmeow";
    private static final String NOTIF_TESLA_CHANNEL = "tesla";
    private static final String NOTIF_EDISON_CHANNEL = "edison";
    private static final int REQUEST_GPS_PERMISSION = 1;
    private static final int REQUEST_BLUETOOTH_PERMISSION = 2;

    private NotificationManager notifMan;
    private SensorManager sensorMan;
    private LocationManager gMan;

    private Sensor accelerometer;
    private Sensor light;

    private EditText txtNotification;
    private TextView txtX;
    private TextView txtY;
    private TextView txtZ;
    private TextView txtLight;
    private TextView txtLatitude;
    private TextView txtLongitude;
    private TextView txtAltitude;
    private RecyclerView rAccel;
    private SensorRecyclerAdapter rAccelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Grab Ui Components
        txtNotification = findViewById(R.id.txtNotification);
        txtX = findViewById(R.id.txtX);
        txtY = findViewById(R.id.txtY);
        txtZ = findViewById(R.id.txtZ);
        txtLight = findViewById(R.id.txtLight);
        txtLatitude = findViewById(R.id.txtLatitude);
        txtLongitude = findViewById(R.id.txtLongitude);
        txtAltitude = findViewById(R.id.txtAltitude);
        rAccel = findViewById(R.id.rAccel);

        rAccelAdapter = new SensorRecyclerAdapter(this);
        rAccel.setLayoutManager(new GridLayoutManager(this, 3));
        rAccel.setAdapter(rAccelAdapter);

        // Grab System Services
        notifMan = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        sensorMan = (SensorManager) getSystemService(SENSOR_SERVICE);
        gMan = (LocationManager) getSystemService(LOCATION_SERVICE);

        setupSensors();
    }

    private void setupSensors() {
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        light = sensorMan.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorMan.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorMan.registerListener(sensorListener, light, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor == accelerometer && event.values.length >= 3) {
                txtX.setText("X: " + event.values[0]);
                txtY.setText("Y: " + event.values[1]);
                txtZ.setText("Z: " + event.values[2]);
                rAccelAdapter.addAccelSet(new AccelSet(event.values[0], event.values[1], event.values[2]));
            }

            if (event.sensor == light && event.values.length >= 1) {
                txtLight.setText("Light: " + event.values[0]);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
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

    private Notification getNotification(String channel) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channel);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(channel)
                .setContentText(txtNotification.getText());

        return builder.build();
    }

    public void setupGPS(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // If we have permissions
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            ) { // We have permission
                startGps();
            } else { // We DON'T have permission, so request them
                // Have they checked "don't ask again"?
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) ||
                        shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
                ){
                    Toast.makeText(this, "I need GPS sir, allow permissions", Toast.LENGTH_SHORT).show();
                    return;
                }

                String[] permissions = {
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                };
                requestPermissions(permissions, REQUEST_GPS_PERMISSION);
            }
        } else {
            // Older devices will have permission
            startGps();
        }

    }

    private void startGps() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Criteria criteria = new Criteria();
        gMan.requestLocationUpdates(500, 0, criteria, location -> {
            txtLatitude.setText("Latitude: " + location.getLatitude());
            txtLongitude.setText("Longitude: " + location.getLongitude());
            txtAltitude.setText("Altitude: " + location.getAltitude());
        }, Looper.getMainLooper());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        switch (requestCode){
            case REQUEST_GPS_PERMISSION:
                setupGPS(null);
                break;
            case REQUEST_BLUETOOTH_PERMISSION:
                // do something
                break;
        }
    }
}