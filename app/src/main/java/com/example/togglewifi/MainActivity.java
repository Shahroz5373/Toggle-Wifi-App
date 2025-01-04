package com.example.togglewifi;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioButton radioOn;
    private RadioButton radioOff;
    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        radioOn = findViewById(R.id.radioOn);
        radioOff = findViewById(R.id.radioOff);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);


        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);


        if (wifiManager == null) {
            radioOn.setEnabled(false);
            radioOff.setEnabled(false);
            Toast.makeText(this, "WiFi not supported on this device", Toast.LENGTH_SHORT).show();
        } else {
            // Set initial state of radio buttons
            if (wifiManager.isWifiEnabled()) {
                radioOn.setChecked(true);
            } else {
                radioOff.setChecked(true);
            }
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                boolean enableWifi = (checkedId == R.id.radioOn);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                    Intent intent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Redirecting to WiFi settings", Toast.LENGTH_SHORT).show();
                } else {

                    if (wifiManager != null) {
                        boolean success = wifiManager.setWifiEnabled(enableWifi);
                        if (success) {
                            String state = enableWifi ? "enabled" : "disabled";
                            Toast.makeText(MainActivity.this, "WiFi " + state, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Failed to change WiFi state", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
