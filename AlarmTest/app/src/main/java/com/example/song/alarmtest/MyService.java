package com.example.song.alarmtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class MyService extends Service {
    String a;
    int time;
    Bundle serviceIntent;

    NotificationManager Notifi_M;
    ServiceThread thread;
    Notification Notifi ;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //서비스가 시작될 때 할 작업
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        serviceIntent = intent.getExtras();
        a = serviceIntent.getString("test");
        time = serviceIntent.getInt("Time");
        Log.d("test", ""+time);

        Notifi_M = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        myServiceHandler handler = new myServiceHandler();
        thread = new ServiceThread(handler, time);
        thread.run();
        return START_STICKY;
    }

    //서비스가 종료될 때 할 작업
    public void onDestroy() {
        thread.stopForever();
        thread = null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.
    }

    class myServiceHandler extends Handler {
        @Override
        public void handleMessage(android.os.Message msg) {
            Intent intent = new Intent(MyService.this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            Notifi = new Notification.Builder(getApplicationContext())
                    .setContentTitle(""+a)
                    .setContentText("Content Text")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setTicker("알림!!!")
                    .setContentIntent(pendingIntent)
                    .build();

            //소리추가
            Notifi.vibrate = new long[] {100,200,100,300,400};

            //알림 소리를 한번만 내도록
            //Notifi.flags = Notification.FLAG_ONLY_ALERT_ONCE;

            //확인하면 자동으로 알림이 제거 되도록
            Notifi.flags = Notification.FLAG_AUTO_CANCEL;


            Notifi_M.notify( 777 , Notifi);

        }
    };
}