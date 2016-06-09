package com.example.davidsun.phone;

import android.app.Service;
import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by davidsun on 4/24/16.
 */
public class Ringtone extends Service{
    MediaPlayer mediasong;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent, int flags, int startId){
        mediasong=MediaPlayer.create(this, R.raw.whistle);
        mediasong.start();
        return START_NOT_STICKY;
    };
    public void onDestroy(){
        Toast.makeText(this, "destroyed", Toast.LENGTH_SHORT).show();
    }
}
