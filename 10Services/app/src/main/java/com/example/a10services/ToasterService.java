package com.example.a10services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ToasterService extends Service {

    private final String[] messages = {
            "Does anyone smell burnt toast?",
            "Did you pour cold water on my hand?",
            "He mapped the human brain."
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        for(int i=0; i<messages.length; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logInfo(messages[i]);
        }

    }

    private void logInfo(String msg){
        Log.i(this.getClass().getName(), msg);
    }
}
