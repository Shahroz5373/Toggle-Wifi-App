package com.example.togglewifi;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioOn;
    private RadioButton radioOff;
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize your RadioButtons
        radioOn = findViewById(R.id.radioOn);
        radioOff = findViewById(R.id.radioOff);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        // Set initial state based on WiFi status
        if (wifiManager.isWifiEnabled()) {
            radioOn.setChecked(true);
        } else {
            radioOff.setChecked(true);
        }

        // Set listener for RadioButton changes
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                boolean enableWifi = checkedId == R.id.radioOn;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Intent intent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                    startActivity(intent);
                } else {
                    wifiManager.setWifiEnabled(enableWifi);
                }
            }
        });
    }
}
