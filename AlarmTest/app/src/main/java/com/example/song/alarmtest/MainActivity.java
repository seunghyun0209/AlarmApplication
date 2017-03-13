package com.example.song.alarmtest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button startbt;
    Button stopbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), Boardcast.class);

        intent.putExtra("test","test");

        final PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        final Calendar calendar = Calendar.getInstance();


        startbt = (Button) findViewById(R.id.button1);

        startbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Test", "AlarmStart1");
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR), 13, 0);
                am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
                Log.d("Test", "Time"+ calendar.get(Calendar.HOUR));
                Log.d("Test", "am Start");
            }
        });


        stopbt = (Button) findViewById(R.id.button2);

        stopbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                am.cancel(sender);
                Log.d("Test", "AlarmStop"+am.toString());
            }
        });


    }
}
