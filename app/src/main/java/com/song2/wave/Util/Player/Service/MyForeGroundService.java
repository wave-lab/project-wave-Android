package com.song2.wave.Util.Player.Service;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.song2.wave.R;
import com.song2.wave.UI.MusicTestActivity;

import java.io.IOException;

public class MyForeGroundService extends Service {
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    MediaPlayer mediaPlayer;
    String MP3_URL = "https://my-data-server.s3.ap-northeast-2.amazonaws.com/JangBumJune3rd-02.mp3";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MusicTestActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.android_app_icon)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(MP3_URL);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();}

        //do heavy work on a background thread

        //stopSelf();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    void onPause() {
        Log.v("Service", "테스트로그");
        mediaPlayer.stop();
        mediaPlayer.release();

    }
}