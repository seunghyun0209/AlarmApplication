package com.example.song.alarmtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Song on 2017-03-13.
 */

public class Boardcast extends BroadcastReceiver{

    String test;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationmanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Bundle testintent = intent.getExtras();
        test = testintent.getString("test");

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher).setTicker("HETT").setWhen(System.currentTimeMillis())
                .setNumber(1).setContentTitle("푸쉬 제목").setContentText(test)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(pendingIntent).setAutoCancel(true);
        Log.d("Test", "CheckBoardcast");
        notificationmanager.notify(1, builder.build());
    }
}
