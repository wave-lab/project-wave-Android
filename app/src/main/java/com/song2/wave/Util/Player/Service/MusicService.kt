package com.song2.wave.Util.Player.Service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.song2.wave.R
import com.song2.wave.R.mipmap.ic_launcher



class MusicService : Service() {


    internal var MESSAGE_KEY = 0
    var TAG = "Music Service"
    var MP3_URL : String = "https://my-data-server.s3.ap-northeast-2.amazonaws.com/JangBumJune3rd-02.mp3"


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

    //    var message : Boolean = intent!!.extras.getBoolean(-.);

        val notiEx = NotificationCompat.Builder(this@MusicService)
            .setContentTitle("Title Example")
            .setContentText("Content Text Example")
            .setSmallIcon(R.mipmap.ic_launcher)
            .build()
      //  startForeground(NOTI_ID, notification)

        return Service.START_STICKY

        Log.v(TAG, "Call Music Service onStartCommand")
        Log.v("Service", "테스트로그5")
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(MP3_URL)
        mediaPlayer.prepare()
        mediaPlayer.start()

        return START_REDELIVER_INTENT;
 //       return super.onStartCommand(intent, flags, startId)

    }

    override fun onDestroy() {
        Log.v("Service", "테스트로그4")
        Log.v(TAG, "Call Music Service onDestroy")
        mediaPlayer.stop()
        super.onDestroy()
    }

    lateinit var mediaPlayer : MediaPlayer

    override fun onBind(intent: Intent?): IBinder {
        Log.v("Service", "테스트로그3")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onCreate() {
        Log.v("Service", "테스트로그2")
        Log.v(TAG, "Call Music Service onCreate")
        super.onCreate()
    }

    fun onPause() {
        Log.v("Service", "테스트로그")
        mediaPlayer.stop()
        mediaPlayer.release()

    }
}