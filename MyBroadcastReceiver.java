package com.example.broadcastreceiverapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case Intent.ACTION_BATTERY_CHANGED:
                    handleBatteryChange(context, intent);
                    break;
                case ConnectivityManager.CONNECTIVITY_ACTION:
                    handleConnectivityChange(context);
                    break;
            }
        }
    }

    private void handleBatteryChange(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level * 100 / (float) scale;
        Log.i("BroadcastReceiver", "Battery percentage: " + batteryPct);
        if (batteryPct >= 89 && batteryPct <= 91) {
            Toast.makeText(context, "Battery is about 90%", Toast.LENGTH_LONG).show();
        }
    }

    private void handleConnectivityChange(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            Toast.makeText(context, "Internet is connected", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Internet is disconnected", Toast.LENGTH_SHORT).show();
        }
    }
}
