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


        final Calendar calendar = Calendar.getInstance();

        startbt = (Button) findViewById(R.id.button1);

        startbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Test", "AlarmStart1");
                //calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR), 13, 0);

                Log.d("Test", "Time"+ calendar.get(Calendar.HOUR));
                Log.d("Test", "am Start");

                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("test","test");
                intent.putExtra("Time", 18);

                startService(intent);
            }
        });

        stopbt = (Button) findViewById(R.id.button2);

        stopbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                stopService(intent);

            }
        });

    }
}
