package com.song2.wave.Util.Player.Service;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.song2.wave.R;
import com.song2.wave.UI.MainPlayer.MainPlayerActivity;
import com.song2.wave.UI.MusicTestActivity;

import java.io.IOException;

public class MyForeGroundService extends Service {
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    MediaPlayer mediaPlayer;
    boolean isPrepared;
    String MP3_URL = "https://my-data-server.s3.ap-northeast-2.amazonaws.com/JangBumJune3rd-02.mp3";

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        int flag = intent.getIntExtra("flag",0);
        if(flag == 0){
            Log.v("Service", "flag = 시작");
            createNotificationChannel();
            Intent notificationIntent = new Intent(this, MainPlayerActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, notificationIntent, 0);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Foreground Service")
                    .setContentText(input)
                    .setSmallIcon(R.drawable.android_app_icon)
                    .setContentIntent(pendingIntent)
                    .build();

            startForeground(1, notification);


            try {
                mediaPlayer.setDataSource(MP3_URL);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();}

            //do heavy work on a background thread

            //stopSelf();
        }
        else if(flag == 1){
            mediaPlayer.pause();
            Log.v("Service", "flag = 일시정지");
        }
        else if(flag == 2){
            int playbackPosition = intent.getIntExtra("playbackPosition",0);
            mediaPlayer.seekTo(playbackPosition);
            mediaPlayer.start();
            Log.v("Service", "flag = 재시작");
        }


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





    public int getDuration(){
        return mediaPlayer.getDuration();
    }

    public int getCurrentDuration(){
        return mediaPlayer.getCurrentPosition();
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

    // 음악 재생 가능 준비
    private void prepare() {
        try {
            mediaPlayer.setDataSource(MP3_URL);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 음악 시작 - 최초 시작
    public void play(int position) {
        stop();
        prepare();
    }

    // 음악 시작 - 최초 시작 X
    public void play() {
        if (isPrepared) {
            mediaPlayer.start();
        }
    }

    // 음악 일시정지
    public void pause() {
        if (isPrepared) {
            mediaPlayer.pause();
        }
    }

    // 음악 종료
    private void stop() {
        mediaPlayer.stop();
        mediaPlayer.reset();
    }


}