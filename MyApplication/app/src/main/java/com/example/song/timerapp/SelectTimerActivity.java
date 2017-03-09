package com.example.song.timerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by Song on 2017-03-09.
 */

public class SelectTimerActivity extends AppCompatActivity{

    TimePicker tp;
    Button Savebt;
    int Time;
    int min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_timer);
        final Intent intent = new Intent();
        tp = (TimePicker) findViewById(R.id.timePicker);
        Savebt = (Button) findViewById(R.id.Savebt);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Time = hourOfDay;
                min = minute;
                Toast.makeText(SelectTimerActivity.this, ""+hourOfDay+ " : " +minute, Toast.LENGTH_SHORT).show();
            }
        });

        Savebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Time", Time);
                intent.putExtra("Min", min);
                setResult(RESULT_OK,intent);
                finish();
            }
        });



    }
}
