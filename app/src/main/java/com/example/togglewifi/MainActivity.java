package com.example.togglewifi;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_On = findViewById(R.id.On);
        Button button_Off = findViewById(R.id.Off);

        button_On.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    startActivity(intent);
                } else {

                    WifiManager wifiManager = (WifiManager) getApplicationContext()
                            .getSystemService(WIFI_SERVICE);
                    wifiManager.setWifiEnabled(true);
                }
            }
        });

        button_Off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    startActivity(intent);
                } else {

                    WifiManager wifiManager = (WifiManager) getApplicationContext()
                            .getSystemService(WIFI_SERVICE);
                    wifiManager.setWifiEnabled(false);
                }
            }
        });
    }
}
