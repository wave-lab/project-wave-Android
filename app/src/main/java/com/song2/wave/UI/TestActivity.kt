package com.song2.wave.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.song2.wave.R
import kotlinx.android.synthetic.main.activity_test.*
import com.song2.wave.UI.Main.MainActivity
import android.content.Intent
import android.util.Log
import com.song2.wave.Util.Player.Service.MusicService


class TestActivity : AppCompatActivity() {

    var TAG = "TestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        btn_test_act_music_start.setOnClickListener {
            val intent = Intent(this@TestActivity, MusicService::class.java)
            startService(intent)
            Log.v(TAG,"startService")
        }

        btn_test_act_music_stop.setOnClickListener {
            val intent = Intent(this@TestActivity, MusicService::class.java)
            stopService(intent)
            Log.v(TAG,"stopService")
        }
    }
}
