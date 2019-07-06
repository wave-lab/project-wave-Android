package com.song2.wave.UI;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class TestService extends Service {

    static int MESSAGE_KEY = 0;

    public class LocalBinder extends Binder{
        public TestService getService(){
            return TestService.this;
        }
    }

    private IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent){
        return mBinder;
    }

    public int showTheNumber(){
        return new Random().nextInt(99);
    }

}
