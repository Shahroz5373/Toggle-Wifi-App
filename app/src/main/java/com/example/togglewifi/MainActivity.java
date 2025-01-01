package com.example.togglewifi;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Switch wifiSwitch;
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifiSwitch = findViewById(R.id.wifiSwitch);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        // Check the current Wi-Fi state and set the switch accordingly
        if (wifiManager.isWifiEnabled()) {
            wifiSwitch.setChecked(true);
        } else {
            wifiSwitch.setChecked(false);
        }

        // Set a listener for the switch
        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Intent intent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                    startActivity(intent);
                } else {
                    // Toggle Wi-Fi state
                    wifiManager.setWifiEnabled(isChecked);
                }
            }
        });
    }
}