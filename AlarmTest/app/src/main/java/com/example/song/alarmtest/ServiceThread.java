package com.example.song.alarmtest;

import android.os.Handler;
import android.util.Log;
import java.util.Calendar;

/**
 * Created by Song on 2017-03-23.
 */

public class ServiceThread extends Thread {
    Handler handler;
    boolean isRun = true;
    int Setting_Time;

    public ServiceThread(Handler handler, int time){
        this.handler = handler;
        this.Setting_Time = time;
    }

    public void stopForever(){
        synchronized (this) {
            this.isRun = false;
        }
    }

    public void run() {
        while(isRun){
            final Calendar calendar = Calendar.getInstance();
            int current_Time;
            current_Time = calendar.get(Calendar.MINUTE);
            Log.d("Test","Thread Test - "+isRun);
            Log.d("Test","Checking Time - "+current_Time);
            if(current_Time == Setting_Time){
                handler.sendEmptyMessage(1);
                Log.d("Test", "Check if for Thread");
                this.isRun = false;
            }
            else{
                try {
                    Log.d("Test", "sleep Test");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}