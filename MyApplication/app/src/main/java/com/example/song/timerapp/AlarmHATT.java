package com.example.song.timerapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Song on 2017-03-13.
 */
public class AlarmHATT extends AppCompatActivity{
    private Context context;
    private int atime;
    private int amin;
    private String memo;
    Intent intent;
    PendingIntent sender;

    public AlarmHATT(Context context, int time, int min, String memo) {
        this.atime = time;
        this.amin = min;
        this.memo = memo;
        this.context = context;
        intent = new Intent(context, Broadcast.class);
    }

    public void Alarm() {

        intent.putExtra("Memo", memo);
        intent.putExtra("Time", atime);
        intent.putExtra("Min", amin);

        Log.d("Test", memo+", "+atime+", "+amin);

        sender = PendingIntent.getBroadcast(context, 0, intent, 0);


        Calendar calendar = Calendar.getInstance();
        //알람시간 calendar에 set해주기

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), atime, amin, 0);
        Log.d("Test", "TimerTest");

        //알람 예약
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
    }

    public void AlarmClose(){
        //am.cancel(sender);
    }
}