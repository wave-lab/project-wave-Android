package com.song2.wave;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.song2.wave.UI.Main.MainActivity;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("state", "launch");
        startActivity(intent);
        finish();
    }
}