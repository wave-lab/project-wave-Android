package com.song2.wave.Util.Player.Service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MusicService : Service() {

    var TAG = "Music Service"
    var MP3_URL = "https://github.com/nzkozar/ExoplayerExample/blob/master/sample.m4a?raw=true"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.v(TAG, "Call Music Service onStartCommand")
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(MP3_URL)
        mediaPlayer.prepare()
        mediaPlayer.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.v(TAG, "Call Music Service onDestroy")
        mediaPlayer.stop()
        super.onDestroy()
    }

    lateinit var mediaPlayer : MediaPlayer

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onCreate() {
        Log.v(TAG, "Call Music Service onCreate")
        super.onCreate()
    }
}