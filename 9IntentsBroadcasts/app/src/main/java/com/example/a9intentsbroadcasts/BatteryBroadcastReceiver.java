package com.example.a9intentsbroadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class BatteryBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int percent = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1);
        Toast.makeText(context, "Battery at " + percent +"%", Toast.LENGTH_SHORT).show();
    }
}
