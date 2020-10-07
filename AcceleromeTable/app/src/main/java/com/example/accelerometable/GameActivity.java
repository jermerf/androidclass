package com.example.accelerometable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.accelerometable.model.User;

import java.util.Calendar;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    public static final int FPS = 30;
    public static final String EXTRA_USER = "user";

    private SensorManager sensors;
    private Sensor accelerometer;
    private Handler timer;
    private int timerDelay = 33;

    private Game game;
    private User user;

    public static int w, h;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = (User) getIntent().getSerializableExtra(EXTRA_USER);

        getSupportActionBar().setTitle(user.getUsername());

        sensors = (SensorManager) getSystemService(SENSOR_SERVICE);

        accelerometer = sensors.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensors.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                game.fX = event.values[0];
                game.fY = event.values[1];
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        }, accelerometer, SensorManager.SENSOR_DELAY_GAME);

        timer = new Handler(Looper.myLooper());
        timerDelay = 1000 / FPS;

        w = getWindowManager().getDefaultDisplay().getWidth();
        h = getWindowManager().getDefaultDisplay().getHeight();

        game = new Game(this);
        setContentView(game);
    }

    private class Game extends View {
        static final float SPEED_MULTIPLIER = 10;
        static final float COLLISION_RADIUS = 50;
        static final int TARGET_PADDING = 200;
        float fX = 0;
        float fY = 0;

        float x = 0;
        float y = 0;
        float targetX = 400;
        float targetY = 400;

        int score = 0;

        void gameLoop() {
            // Update position
            x -= fX * SPEED_MULTIPLIER;
            y += fY * SPEED_MULTIPLIER;

            // Check for collision
            double d = Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
            if (d < COLLISION_RADIUS) {
                score++;
                randomTarget();
            }
        }

        public void randomTarget() {
            Random rnd = new Random(Calendar.getInstance().getTimeInMillis());
            targetX = TARGET_PADDING + rnd.nextInt(w - 2 * TARGET_PADDING);
            targetY = TARGET_PADDING + rnd.nextInt(h - 2 * TARGET_PADDING);
        }

        public Game(Context context) {
            super(context);
            randomTarget();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            gameLoop();
            canvas.drawBitmap(bmp(R.drawable.player), x, y, null);
            canvas.drawBitmap(bmp(R.drawable.target), targetX, targetY, null);

            timer.postDelayed(() -> invalidate(), timerDelay);
        }

        private Bitmap bmp(int id) {
            return BitmapFactory.decodeResource(getResources(), id);
        }
    }
}
