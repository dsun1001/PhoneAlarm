package com.example.davidsun.phone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by davidsun on 4/24/16.
 */
public class Alarm_Receiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in the receiver","Yay");
        Intent service_intent = new Intent(context, Ringtone.class);
        context.startService(service_intent);
    }
}
