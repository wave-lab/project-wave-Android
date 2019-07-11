package com.song2.wave.AudioTest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;

public class AudioServiceInterface {
    private ServiceConnection mServiceConnection;
    private AudioService mService;

    public AudioServiceInterface(Context context) {
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = ((AudioService.AudioServiceBinder) service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mServiceConnection = null;
                mService = null;
            }
        };
        context.bindService(new Intent(context, AudioService.class).setPackage(context.getPackageName()), mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void setPlayList(ArrayList<Long> audioIds) {
        if (mService != null) {
            mService.setPlayList(audioIds);
        }
    }

//    public void play(int position) {
//        if (mService != null) {
//            Log.v("asdf","테스트1");
//            mService.play(position);
//        }
//    }

    public void play(String _id, String songUrl, String originArtist, String coverArtist, String songName) {
        if (mService != null) {
            mService.play(_id, songUrl, originArtist, coverArtist, songName);
            Log.v("Asdf","테스트 플레ㅣ");
        }
    }


    public void play() {
        if (mService != null) {
            mService.play();
        }
    }

    public void togglePlay() {
        if (isPlaying()) {
            mService.pause();
        } else {
            mService.play();
        }
    }

    public boolean isPlaying() {
        if (mService != null) {
            return mService.isPlaying();
        }

        return false;
    }

    public AudioAdapter.AudioItem getAudioItem() {
        if (mService != null) {
            return mService.getAudioItem();
        }
        return null;
    }

    public void pause() {
        if (mService != null) {
            mService.play();
        }
    }

    public void forward() {
        if (mService != null) {
            mService.forward();
        }
    }

    public void rewind() {
        if (mService != null) {
            mService.rewind();
        }
    }
}