package com.example.broadcastreceiverapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver objReceiver;
    private ConnectivityChangeReceiver connectivityChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Use your custom layout resource

        // Register the battery change receiver
        objReceiver = new MyBroadcastReceiver();
        IntentFilter batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(objReceiver, batteryFilter);

        // Register the connectivity change receiver dynamically
        connectivityChangeReceiver = new ConnectivityChangeReceiver();
        IntentFilter connectivityFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectivityChangeReceiver, connectivityFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (objReceiver != null) {
            unregisterReceiver(objReceiver);
        }
        if (connectivityChangeReceiver != null) {
            unregisterReceiver(connectivityChangeReceiver);
        }
    }
}
