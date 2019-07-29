package com.song2.wave.Util.Audio;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import com.song2.wave.R;
import com.song2.wave.UI.MainPlayer.MainPlayerActivity;
import com.squareup.picasso.Picasso;

public class NotificationPlayer {
    private final static int NOTIFICATION_PLAYER_ID = 0x342;
    private AudioService mService;
    private NotificationManager mNotificationManager;
    private NotificationManagerBuilder mNotificationManagerBuilder;
    private boolean isForeground;
    String title, originArtist, coverArtist, songImgUrl, _id;
    int rating_flag;

    String channelName = "Channel Name";


    public NotificationPlayer(AudioService service) {
        mService = service;
        mNotificationManager = (NotificationManager) service.getSystemService(Context.NOTIFICATION_SERVICE);

    }


    @SuppressLint("StaticFieldLeak")
    public void updateNotificationPlayer() {
        //cancel();
        mNotificationManagerBuilder = new NotificationManagerBuilder();
        mNotificationManagerBuilder.execute();

    }

    public void removeNotificationPlayer() {

        Log.v("asdf", "테스트12");
       speicialCancel();
        // cancel();
        mService.stopForeground(true);
        isForeground = false;
    }

    private void speicialCancel(){

        if (mNotificationManagerBuilder != null) {

            mNotificationManager.cancelAll();
            mNotificationManagerBuilder = null;
        }
    }

    private void cancel() {
        Log.v("asdf", "테스트종료1");
        if (mNotificationManagerBuilder != null) {

            Log.v("asdf", "테스트종료2");
            mNotificationManagerBuilder.cancel(true);
            mNotificationManagerBuilder = null;
        }
    }

    private class NotificationManagerBuilder extends AsyncTask<Void, Void, Notification> {
        private RemoteViews mRemoteViews;
        private NotificationCompat.Builder mNotificationBuilder;
        private PendingIntent mMainPendingIntent;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            createNotificationChannel();

            Intent playActivity = new Intent(mService, MainPlayerActivity.class);

            mRemoteViews = createRemoteView(R.layout.notification_player);
            mNotificationBuilder = new NotificationCompat.Builder(mService, "123");
            mNotificationBuilder.setSmallIcon(R.drawable.img_home_wavelogo)
//                    .setContentTitle("Foreground Service")
                    .setOngoing(true)
//                    .setContentIntent(mMainPendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContent(mRemoteViews);

            Notification notification = mNotificationBuilder.build();
            notification.contentIntent = mMainPendingIntent;

            playActivity.putExtra("_id", MainPlayerActivity.mainPlayerActivity.get_id());
            playActivity.putExtra("title", MainPlayerActivity.mainPlayerActivity.getTitle());
            playActivity.putExtra("originArtist", MainPlayerActivity.mainPlayerActivity.getOriginArtist());
            playActivity.putExtra("coverArtist", MainPlayerActivity.mainPlayerActivity.getCoverArtist());
            playActivity.putExtra("rating_flag", rating_flag);

            //playActivity.putExtra("songImgUrl", MainPlayerActivity.mainPlayerActivity.getSongImgUrl());
            playActivity.putExtra("flag", 1);
            mMainPendingIntent = PendingIntent.getActivity(mService, 0, playActivity, PendingIntent.FLAG_UPDATE_CURRENT);

            if (!isForeground) {
                isForeground = true;
                // 서비스를 Foreground 상태로 만든다
                mService.startForeground(NOTIFICATION_PLAYER_ID, notification);
            }
        }

        @Override
        protected Notification doInBackground(Void... params) {
            mNotificationBuilder.setContent(mRemoteViews);
            mNotificationBuilder.setContentIntent(mMainPendingIntent);
            Notification notification = mNotificationBuilder.build();
            updateRemoteView(mRemoteViews, notification);
            return notification;
        }

        @Override
        protected void onPostExecute(Notification notification) {
            super.onPostExecute(notification);
            try {
                mNotificationManager.notify(NOTIFICATION_PLAYER_ID, notification);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void createNotificationChannel() {

            int importance = NotificationManager.IMPORTANCE_HIGH;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


                NotificationChannel serviceChannel = new NotificationChannel(
                        "123","뮤직",
                        NotificationManager.IMPORTANCE_HIGH);
                NotificationManager manager = mService.getSystemService(NotificationManager.class);
                manager.createNotificationChannel(serviceChannel);

            }
        }

        private RemoteViews createRemoteView(int layoutId) {
            RemoteViews remoteView = new RemoteViews(mService.getPackageName(), layoutId);
            Intent actionTogglePlay = new Intent(CommandActions.TOGGLE_PLAY);
            Intent actionForward = new Intent(CommandActions.FORWARD);
            Intent actionRewind = new Intent(CommandActions.REWIND);
            Intent actionClose = new Intent(CommandActions.CLOSE);
            PendingIntent togglePlay = PendingIntent.getService(mService, 0, actionTogglePlay, 0);
            PendingIntent forward = PendingIntent.getService(mService, 0, actionForward, 0);
            PendingIntent rewind = PendingIntent.getService(mService, 0, actionRewind, 0);
            PendingIntent close = PendingIntent.getService(mService, 0, actionClose, 0);

            remoteView.setOnClickPendingIntent(R.id.btn_play_pause, togglePlay);
            remoteView.setOnClickPendingIntent(R.id.btn_forward, forward);
            remoteView.setOnClickPendingIntent(R.id.btn_rewind, rewind);
            remoteView.setOnClickPendingIntent(R.id.btn_close, close);
            return remoteView;
        }

        private void updateRemoteView(RemoteViews remoteViews, Notification notification) {
            if (mService.isPlaying()) {
                remoteViews.setImageViewResource(R.id.btn_play_pause, R.drawable.pause_btn);
            } else {
                remoteViews.setImageViewResource(R.id.btn_play_pause, R.drawable.restart_btn);
            }

//            String title = mService.getAudioItem().mTitle;
            _id = mService._id;
            title = mService.songName;
            originArtist = mService.originArtist;
            coverArtist = mService.coverArtist;
            rating_flag = mService.ratingFlag;

            remoteViews.setTextViewText(R.id.txt_title, title);
            remoteViews.setTextViewText(R.id.cover_atist_name, coverArtist);
//            Uri albumArtUri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), mService.getAudioItem().mAlbumId);
            Handler uiHandler = new Handler(Looper.getMainLooper());
            uiHandler.post(new Runnable(){
                @Override
                public void run() {
                    Picasso.with(mService).load("SAdf").error(R.drawable.kakao_default_profile_image).into(remoteViews, R.id.img_albumart, NOTIFICATION_PLAYER_ID, notification);
                }
            });
        }
    }
}
