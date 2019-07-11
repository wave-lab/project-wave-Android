package com.song2.wave.AudioTest;

import android.app.*;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import com.song2.wave.UI.Main.MainActivity;
import com.song2.wave.UI.MainPlayer.MainPlayerActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AudioService extends Service {
    private final IBinder mBinder = new AudioServiceBinder();
    private ArrayList<Long> mAudioIds = new ArrayList<>();
    private MediaPlayer mMediaPlayer;
    boolean isPrepared;
    int playbackPosition = 0;
    boolean isPlaying = false;
    private int mCurrentPosition;
    private AudioAdapter.AudioItem mAudioItem;
    private NotificationPlayer mNotificationPlayer;

    String playTime;
    long mpCurrentPosition1, mpCurrentPosition2;
    String songUrl, songName, originArtist, coverArtist, title, songImgUrl, _id;


    public class AudioServiceBinder extends Binder {
        AudioService getService() {
            return AudioService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                isPrepared = true;
                mp.start();

                String lengthOfSong = String.format("%02d:%02d", ((mp.getDuration() / 1000) % 3600 / 60), ((mp.getDuration() / 1000) % 3600 % 60));
                MainPlayerActivity.mainPlayerActivity.lengthTimeTv.setText(lengthOfSong);
                MainPlayerActivity.mainPlayerActivity.seekbar.setMax(mp.getDuration());

                //mTimer = new Timer();
                //mTimer.schedule(mTask, 100);

                sendBroadcast(new Intent(BroadcastActions.PREPARED)); // prepared 전송
                updateNotificationPlayer();
            }
        });

        Handler mSeekbarUpdateHandler = new Handler();
        Runnable mUpdateSeekbar = new Runnable() {
            @Override
            public void run() {
                if(isPrepared){
                    Log.v("Asdf","텟텟 = " + mMediaPlayer.getCurrentPosition());
                    MainPlayerActivity.mainPlayerActivity.seekbar.setProgress(mMediaPlayer.getCurrentPosition());
                    if(MainPlayerActivity.mainPlayerActivity.seekbar.getMax() > 0) {
                        playTime = String.format(
                                "%02d:%02d",
                                getMpCurrentPosition1(),
                                getMpCurrentPosition2()
                        );
                        MainPlayerActivity.mainPlayerActivity.durationTimeTv.setText(playTime);
                    }

                    MainPlayerActivity.mainPlayerActivity.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        //seek바의 값이 변경되었을때 + fromUser: Boolean : 터치를 통해 변경했으면 false , 코드를 통하면 true
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (MainPlayerActivity.mainPlayerActivity.seekbar.getMax() == progress) {
                                isPlaying = false;
                                mMediaPlayer.stop();
                            }
                        }

                        //seek바의 값을 변경하기 위해 터치했을 때
                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                            isPlaying = false;
                            mMediaPlayer.pause();
                        }

                        //값을 변경 한 후 터치를 떼었을 때
                        @Override
                        public void onStopTrackingTouch(SeekBar SeekBar) {
                            isPlaying = true;
                            playbackPosition = MainPlayerActivity.mainPlayerActivity.seekbar.getProgress();
                            mMediaPlayer.seekTo(playbackPosition);

                            if (!SeekBar.isSelected()) {
                                mMediaPlayer.start();
                            } else
                                mMediaPlayer.pause();
                        }
                    });
                }
                mSeekbarUpdateHandler.postDelayed(this, 50);
            }
        };

        mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 0);

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                isPrepared = false;

                forward(); // 다음곡 재생
                sendBroadcast(new Intent(BroadcastActions.PLAY_STATE_CHANGED)); // 재생상태 변경 전송
                updateNotificationPlayer();
            }
        });
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {

                isPrepared = false;
                sendBroadcast(new Intent(BroadcastActions.PLAY_STATE_CHANGED)); // 재생상태 변경 전송
                updateNotificationPlayer();
                return false;
            }
        });
        mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mp) {

            }
        });
        mNotificationPlayer = new NotificationPlayer(this);



    }



    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            if (CommandActions.TOGGLE_PLAY.equals(action)) {
                if (isPlaying()) {
                    pause();
                } else {
                    play();
                }
            } else if (CommandActions.REWIND.equals(action)) {
                rewind();
            } else if (CommandActions.FORWARD.equals(action)) {
                forward();
            } else if (CommandActions.CLOSE.equals(action)) {
                pause();
                removeNotificationPlayer();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        removeNotificationPlayer();
    }


    private void updateNotificationPlayer() {
        if (mNotificationPlayer != null) {
            mNotificationPlayer.updateNotificationPlayer();
        }
    }

    private void removeNotificationPlayer() {
        if (mNotificationPlayer != null) {
            mNotificationPlayer.removeNotificationPlayer();
        }
    }

    private void queryAudioItem(int position) {
        mCurrentPosition = position;
        long audioId = mAudioIds.get(position);
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA
        };
        String selection = MediaStore.Audio.Media._ID + " = ?";
        String[] selectionArgs = {String.valueOf(audioId)};
        Cursor cursor = getContentResolver().query(uri, projection, selection, selectionArgs, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                mAudioItem = AudioAdapter.AudioItem.bindCursor(cursor);
            }
            cursor.close();
        }
    }



    private void prepare(String songUrl) {
        try {
            mMediaPlayer.setDataSource(songUrl);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            //mMediaPlayer.prepare();
            mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        mMediaPlayer.stop();
        mMediaPlayer.reset();
    }

    public void setPlayList(ArrayList<Long> audioIds) {
        if (!mAudioIds.equals(audioIds)) {
            mAudioIds.clear();
            mAudioIds.addAll(audioIds);
        }
    }

    public AudioAdapter.AudioItem getAudioItem() {
        return mAudioItem;
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    public int getDuration(){
        return mMediaPlayer.getDuration();
    }

    public long getMpCurrentPosition1(){
        long resultCurrentValue = ((mMediaPlayer.getCurrentPosition() / 1000) % 3600 / 60);
        return resultCurrentValue;
    }

    public long getMpCurrentPosition2(){
        return ((mMediaPlayer.getCurrentPosition() / 1000) % 3600 % 60);
    }

    public int getCurrentPosition(){
        return ((mMediaPlayer.getCurrentPosition() / 1000) % 3600 % 60);
    }

    public void play(String _id, String songUrl, String originArtist, String coverArtist, String songName) {
//        queryAudioItem(position);
        this._id = _id;
        this.songUrl = songUrl;
        this.originArtist = originArtist;
        this.coverArtist = coverArtist;
        this.songName = songName;
        stop();
        prepare(songUrl);
    }


    public void play() {
        if (isPrepared) {

            mMediaPlayer.start();
            sendBroadcast(new Intent(BroadcastActions.PLAY_STATE_CHANGED)); // 재생상태 변경 전송
            updateNotificationPlayer();
        }
    }

    public void pause() {
        if (isPrepared) {
            mMediaPlayer.pause();
            sendBroadcast(new Intent(BroadcastActions.PLAY_STATE_CHANGED)); // 재생상태 변경 전송
            updateNotificationPlayer();
        }
    }

    public void forward() {
        if (mAudioIds.size() - 1 > mCurrentPosition) {
            mCurrentPosition++; // 다음 포지션으로 이동.
        } else {
            mCurrentPosition = 0; // 처음 포지션으로 이동.
        }
//        play(mCurrentPosition);
    }

    public void rewind() {
        if (mCurrentPosition > 0) {
            mCurrentPosition--; // 이전 포지션으로 이동.
        } else {
            mCurrentPosition = mAudioIds.size() - 1; // 마지막 포지션으로 이동.
        }
//        play(mCurrentPosition);
    }
}