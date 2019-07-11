package com.song2.wave.UI;


import android.Manifest;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.song2.wave.R;
import com.song2.wave.Util.Player.Service.MyForeGroundService;


public class MusicTestActivity extends AppCompatActivity {
    Button btnStartService, btnStopService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_test);
        btnStartService = findViewById(R.id.buttonStartService);
        btnStopService = findViewById(R.id.buttonStopService);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });
    }

    public void startService() {
        Intent serviceIntent = new Intent(this, MyForeGroundService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");


        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService() {
        Intent serviceIntent = new Intent(this, MyForeGroundService.class);
        stopService(serviceIntent);
    }
}