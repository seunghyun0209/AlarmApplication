package com.example.song.timerapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Song on 2017-03-10.
 */

public class Broadcast extends BroadcastReceiver {

    String INTENT_ACTION = Intent.ACTION_BOOT_COMPLETED;
    int Min;
    int Time;
    String Memo;
    Bundle timerIntent;
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationmanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        timerIntent = intent.getExtras();

        Time = timerIntent.getInt("Time");
        Min = timerIntent.getInt("Min");
        Memo = timerIntent.getString("Memo");
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, Timer_Main.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher).setTicker("HETT").setWhen(System.currentTimeMillis())
                .setNumber(1).setContentTitle(Memo).setContentText(Time + " : " +Min)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingIntent).setAutoCancel(true);

        notificationmanager.notify(1, builder.build());

    }
}
